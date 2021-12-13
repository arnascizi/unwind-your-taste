package com.github.uyt.ui.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.RecipeService;
import com.github.uyt.model.Recipe;
import com.github.uyt.ui.view.RecipePreviewView;
import com.github.uyt.ui.view.RecipeView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecipeHelper {

    private final RecipeService recipeService;

    public int getRecipeCount() {
        return recipeService.getAllRecipes().size();
    }

    public List<RecipeView> getAllRecipes(Pageable pageable) {
        ArrayList<RecipeView> list = new ArrayList<>();
        for (Recipe recipe : recipeService.finAllPageable(pageable)) {
            list.add(buildRecipe(recipe));
        }
        return list;
    }

    public List<RecipePreviewView> getAllRecipesPreview(Pageable pageable) {
        ArrayList<RecipePreviewView> list = new ArrayList<>();
        for (Recipe recipe : recipeService.finAllPageable(pageable)) {
            list.add(buildRecipePreview(recipe));
        }
        return list;
    }

    public RecipeView getDetailedRecipeView(Long id) {
        return buildRecipe(recipeService.fetchSingleRecipe(id));
    }

    public List<RecipePreviewView> getRecommendedRecipes() {
        return recipeService.getRecommendedRecipes().stream().map(this::buildRecipePreview).collect(Collectors.toList());
    }

    private RecipePreviewView buildRecipePreview(Recipe recipe) {
        return RecipePreviewView.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .complexity(recipe.getComplexity().getValue())
                .thumbnail(recipe.getImage())
                .build();
    }

    private RecipeView buildRecipe(Recipe recipe) {
        return RecipeView.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .guideline(recipe.getPreparationDescription())
                .serving(recipe.getServing())
                .uploadTime(recipe.getUpdatedAt())
                .updateTime(recipe.getUpdatedAt())
                .uploader(recipe.getUserAccount().getUsername())
                .complexity(recipe.getComplexity().getValue())
                .category(recipe.getCocktailCategory().getName())
                .build();
    }
}
