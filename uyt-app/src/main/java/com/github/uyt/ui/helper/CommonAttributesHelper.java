package com.github.uyt.ui.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.CommonComponentsService;
import com.github.uyt.bl.service.ProductService;
import com.github.uyt.model.CategoryType;
import com.github.uyt.model.CocktailCategory;
import com.github.uyt.model.Complexity;
import com.github.uyt.model.Product;
import com.github.uyt.ui.view.CategoryTypeView;
import com.github.uyt.ui.view.CategoryView;
import com.github.uyt.ui.view.ComplexityView;
import com.github.uyt.ui.view.ProductView;

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

    public List<ProductView> getAllIngredients() {
        return productService.fetchALLProducts().stream().map(this::buildIngredientView).collect(Collectors.toList());
    }

    public List<ComplexityView> getAllComplexities() {
        return commonComponentsService.fetchAllComplexities().stream().map(this::buildComplexityView).collect(Collectors.toList());
    }

    public List<CategoryView> getAllDetailedCategories() {
        return commonComponentsService.fetchAllCategories().stream().map(this::buildDetailedCategoryView).collect(Collectors.toList());
    }

    private CategoryView buildDetailedCategoryView(CocktailCategory category) {
        return CategoryView.builder()
                .id(category.getId())
                .title(category.getName())
                // .categoryType(category.getCategoryType().getValue())
                .build();
    }

    private ComplexityView buildComplexityView(Complexity complexity) {
        return ComplexityView.builder()
                .id(complexity.getId())
                .complexityValue(complexity.getValue())
                .build();
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

    private ProductView buildIngredientView(Product product) {
        return ProductView.builder()
                .id(product.getId())
                .name(product.getName())
                .measurement(product.getMeasurement().getValue())
                .productType(product.getProductType().getName())
                .build();
    }
}
