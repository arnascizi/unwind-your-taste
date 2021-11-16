package com.github.uyt.bl.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.uyt.bl.repository.RecipeRepository;
import com.github.uyt.model.Recipe;

@Component
@Transactional
public class RecipeRepositoryImpl extends SimpleJpaRepository<Recipe, Long> implements RecipeRepository {

    @Autowired
    public RecipeRepositoryImpl(Class<Recipe> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return null;
    }
}
