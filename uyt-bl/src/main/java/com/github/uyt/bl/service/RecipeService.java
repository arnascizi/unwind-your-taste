package com.github.uyt.bl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uyt.bl.repository.RecipeRepository;
import com.github.uyt.model.Recipe;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Page<Recipe> getAllRecipes(@NonNull Pageable pageable) {
        return recipeRepository.fetchAllRecipes(pageable);
    }

    public Page<Recipe> finAllPageable(@NonNull Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe fetchSingleRecipe(@NonNull Long id) {
        return recipeRepository.fetchSingleRecipe(id);
    }
}
