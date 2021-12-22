package com.github.uyt.bl.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.CocktailCategoryRepository;
import com.github.uyt.model.CategoryType;
import com.github.uyt.model.CategoryType_;
import com.github.uyt.model.CocktailCategory;
import com.github.uyt.model.CocktailCategory_;

@Component
@Transactional
public class CocktailCategoryRepositoryImpl extends SimpleJpaRepository<CocktailCategory, Long> implements CocktailCategoryRepository {

    @PersistenceContext private EntityManager em;

    public CocktailCategoryRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(CocktailCategory.class, entityManager), entityManager);
    }

    @Override
    public List<CocktailCategory> fetchCocktailCategoriesByType(String categoryType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CocktailCategory> criteria = cb.createQuery(CocktailCategory.class);
        Root<CocktailCategory> root = criteria.from(CocktailCategory.class);
        Join<CocktailCategory, CategoryType> joinType = root.join(CocktailCategory_.categoryType);
        criteria.select(root).where(cb.equal(joinType.get(CategoryType_.value.getName()), categoryType));
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public List<CocktailCategory> fetchAllCategories() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CocktailCategory> criteria = cb.createQuery(CocktailCategory.class);
        Root<CocktailCategory> root = criteria.from(CocktailCategory.class);
        criteria.select(root).distinct(true);
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public List<CategoryType> fetchAllCategoryTypes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategoryType> criteria = cb.createQuery(CategoryType.class);
        Root<CategoryType> root = criteria.from(CategoryType.class);
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }
}
