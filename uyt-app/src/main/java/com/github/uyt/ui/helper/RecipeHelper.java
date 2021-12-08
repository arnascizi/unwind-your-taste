package com.github.uyt.ui.helper;

import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.RecipeService;
import com.github.uyt.model.Category;
import com.github.uyt.ui.view.CategoryView;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecipeHelper {

    private final RecipeService recipeService;

    private CategoryView buildCategoryView(Category category) {
        return CategoryView.builder()
                .id(category.getId())
                .title(category.getCategoryName())
                .build();
    }

    public RecipePreviewView singleMockRecipe() {
        return RecipePreviewView.builder()
                .id(1L)
                .title("Something")
                .complexity("easyt")
                .build();
    }
}
