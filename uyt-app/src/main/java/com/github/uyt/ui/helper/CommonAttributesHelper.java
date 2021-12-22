package com.github.uyt.ui.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.CommonComponentsService;
import com.github.uyt.bl.service.ProductService;
import com.github.uyt.model.CategoryType;
import com.github.uyt.model.CocktailCategory;
import com.github.uyt.model.Product;
import com.github.uyt.ui.view.CategoryTypeView;
import com.github.uyt.ui.view.CategoryView;
import com.github.uyt.ui.view.IngredientView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommonAttributesHelper {

    private final CommonComponentsService commonComponentsService;
    private final ProductService productService;

    public List<CategoryView> getCocktailCategories(String categoryType) {
        return commonComponentsService.fetchCocktailCategoriesByType(categoryType).stream().map(this::buildCategoryView).collect(Collectors.toList());
    }

    public List<CategoryView> getCocktailCategories() {
        return commonComponentsService.fetchAllCategories().stream().map(this::buildCategoryView).collect(Collectors.toList());
    }

    public List<CategoryTypeView> getAllCategoryTypes() {
        return commonComponentsService.fetchAllCategoryTypes().stream().map(this::buildCategoryTypeView).collect(Collectors.toList());
    }

    public List<IngredientView> getAllIngredients() {
        return productService.fetchALLProducts().stream().map(this::buildIngredientView).collect(Collectors.toList());
    }

    private CategoryView buildCategoryView(CocktailCategory category) {
        return CategoryView.builder()
                .id(category.getId())
                .title(category.getName())
                .description(category.getDescription())
                .build();
    }

    private CategoryTypeView buildCategoryTypeView(CategoryType type) {
        return CategoryTypeView.builder()
                .id(type.getId())
                .type(type.getValue())
                .build();
    }

    private IngredientView buildIngredientView(Product product) {
        return IngredientView.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
}
