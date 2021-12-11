package com.github.uyt.ui.helper;

import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecipeHelper {

    private final RecipeService recipeService;

    public void getRecipes() {
        recipeService.fetchAllRecipes().forEach(recipe -> System.out.println(recipe.toString()));
    }
}
