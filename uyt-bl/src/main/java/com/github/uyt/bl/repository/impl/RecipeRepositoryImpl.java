package com.github.uyt.bl.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
    public Page<Recipe> fetchAllRecipes(Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
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
        int random = (int)(Math.random() * ((23 - 1) + 1));
        criteria.where(cb.equal(root.get(Recipe_.id), random));
        criteria.select(root).distinct(true);
        return em.createQuery(criteria).getResultList().stream().limit(3).collect(Collectors.toList());
    }

    @Override
    public Page<Recipe> fetchSearchResult(Pageable pageable, Search search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        Join<Recipe, CocktailCategory> categoryJoin = root.join(Recipe_.cocktailCategory);
        Join<CocktailCategory, CategoryType> typeJoin = categoryJoin.join(CocktailCategory_.categoryType);

        if (StringUtils.isNotEmpty(search.getCategory())) {
            criteria.where(cb.equal(categoryJoin.get(CocktailCategory_.name), search.getCategory()));
        }

        if (StringUtils.isNotEmpty(search.getCategoryType())) {
            criteria.where(cb.equal(typeJoin.get(CategoryType_.value), search.getCategoryType()));
        }

        criteria.where(cb.like(root.get(Recipe_.title), "%" + search.getSearchValue() + "%"));
        criteria.select(root);
        TypedQuery<Recipe> query = em.createQuery(criteria);
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }
}
