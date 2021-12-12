package com.github.uyt.bl.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.RecipeRepository;
import com.github.uyt.model.Recipe;
import com.github.uyt.model.Recipe_;

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
        criteria.select(root);
        TypedQuery<Recipe> query = em.createQuery(criteria);
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }

    @Override
    public Recipe fetchSingleRecipe(@NonNull Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        criteria.where(cb.equal(root.get(Recipe_.ID), id));
        return em.createQuery(criteria).getSingleResult();
    }
}
