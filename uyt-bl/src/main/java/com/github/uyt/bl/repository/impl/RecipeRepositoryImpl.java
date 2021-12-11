package com.github.uyt.bl.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.RecipeRepository;
import com.github.uyt.model.Product;
import com.github.uyt.model.Product_;
import com.github.uyt.model.Recipe;
import com.github.uyt.model.Recipe_;

@Component
@Transactional
public class RecipeRepositoryImpl extends SimpleJpaRepository<Recipe, Long> implements RecipeRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public RecipeRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(Recipe.class, entityManager), entityManager);
    }

    @Override
    public List<Recipe> fetchAllRecipes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
        Root<Recipe> root = criteria.from(Recipe.class);
        Join<Recipe, Product> productJoin = root.join(Recipe_.PRODUCT_LIST);
        criteria.where(cb.equal(root.get(Recipe_.PRODUCT_LIST), productJoin.get(Product_.RECIPE_LIST)));
        return em.createQuery(criteria).getResultList();
    }
}
