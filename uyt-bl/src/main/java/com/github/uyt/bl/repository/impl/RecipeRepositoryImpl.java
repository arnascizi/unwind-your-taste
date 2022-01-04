package com.github.uyt.bl.repository.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.RecipeRepository;
import com.github.uyt.model.CategoryType;
import com.github.uyt.model.CategoryType_;
import com.github.uyt.model.CocktailCategory;
import com.github.uyt.model.CocktailCategory_;
import com.github.uyt.model.CommonConstants;
import com.github.uyt.model.Composition;
import com.github.uyt.model.Composition_;
import com.github.uyt.model.Product;
import com.github.uyt.model.Product_;
import com.github.uyt.model.Recipe;
import com.github.uyt.model.Recipe_;
import com.github.uyt.model.Search;

import lombok.NonNull;

@Component
@Transactional
public class RecipeRepositoryImpl extends SimpleJpaRepository<Recipe, Long> implements RecipeRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public RecipeRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(Recipe.class, entityManager), entityManager);
    }

    @Override
    public Page<Recipe> fetchAllRecipes(@NonNull Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        criteria.select(root);
        TypedQuery<Recipe> query = em.createQuery(criteria);
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }

    @Override
    public Recipe fetchSingleRecipe(@NonNull Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        criteria.where(cb.equal(root.get(Recipe_.id), id));
        return em.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Recipe> fetchLatestRecipes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);

        criteria.orderBy(cb.desc(root.get(Recipe_.createdAt)));
        return new ArrayList<>(em.createQuery(criteria).getResultList());
    }

    @Override
    public List<Recipe> fetchRandomRecipes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        Join<Recipe, CocktailCategory> categoryJoin = root.join(Recipe_.cocktailCategory);
        Join<CocktailCategory, CategoryType> typeJoin = categoryJoin.join(CocktailCategory_.categoryType);

        List<Predicate> predicates = new ArrayList<>();

        if (LocalTime.now().isAfter(CommonConstants.MORNING) && LocalTime.now().isBefore(CommonConstants.EVENING)) {
            predicates.add(cb.equal(typeJoin.get(CategoryType_.value), CommonConstants.NON_ALCOHOLIC));
        }
        criteria.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public Page<Recipe> fetchSearchResult(@NonNull Pageable pageable, @NonNull Search search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        Join<Recipe, CocktailCategory> categoryJoin = root.join(Recipe_.cocktailCategory);
        Join<CocktailCategory, CategoryType> typeJoin = categoryJoin.join(CocktailCategory_.categoryType);

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(search.getSearchValue())) {
            predicates.add(cb.like(cb.upper(root.get(Recipe_.title)), "%" + search.getSearchValue().toUpperCase(Locale.ROOT) + "%"));
        }
        
        if (StringUtils.isNotEmpty(search.getCategory())) {
            predicates.add(cb.equal(categoryJoin.get(CocktailCategory_.name), search.getCategory()));
        }
        
        if(StringUtils.isNotEmpty(search.getCategoryType())) {
            predicates.add(cb.equal(typeJoin.get(CategoryType_.value), search.getCategoryType()));
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Recipe> query = em.createQuery(criteria);
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }

    @Override
    public List<Recipe> getRecipeByProduct(@NonNull Long productId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        Join<Recipe, Composition> compositionJoin = root.join(Recipe_.productList);
        Join<Composition, Product> productJoin = compositionJoin.join(Composition_.product);

        criteria.select(root).where(cb.equal(productJoin.get(Product_.id), productId));
        return em.createQuery(criteria).getResultList();
    }
}
