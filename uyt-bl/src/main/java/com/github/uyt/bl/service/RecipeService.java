package com.github.uyt.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.RecipeRepository;
import com.github.uyt.model.Recipe;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> fetchAllRecipes() {
        return recipeRepository.getAllRecipes();
    }
}
