package com.github.uyt.ui.helper;

import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecipeHelper {

    private final RecipeRepository recipeRepository;
}
