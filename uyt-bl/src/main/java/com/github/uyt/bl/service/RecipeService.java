package com.github.uyt.bl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.RecipeRepository;
import com.github.uyt.model.Recipe;
import com.github.uyt.model.Search;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
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

    public List<Recipe> getRecommendedRecipes() {
        return recipeRepository.fetchLatestRecipes();
    }

    public Page<Recipe> fetchSearchResult(Pageable pageable, Search search) {
        return recipeRepository.fetchSearchResult(pageable, search);
    }

    public List<Recipe> getRecipesByProduct(Long productId) {
        return recipeRepository.getRecipeByProduct(productId);
    }
}
