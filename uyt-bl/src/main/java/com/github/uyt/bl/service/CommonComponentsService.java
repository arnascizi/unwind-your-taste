package com.github.uyt.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.CocktailCategoryRepository;
import com.github.uyt.model.CategoryType;
import com.github.uyt.model.CocktailCategory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonComponentsService {

    private final CocktailCategoryRepository cocktailCategoryRepository;

    public List<CocktailCategory> fetchCocktailCategoriesByType(String categoryType) {
        return cocktailCategoryRepository.fetchCocktailCategoriesByType(categoryType);
    }

    public List<CocktailCategory> fetchAllCategories() {
        return cocktailCategoryRepository.fetchAllCategories();
    }

    public List<CategoryType> fetchAllCategoryTypes(){
        return cocktailCategoryRepository.fetchAllCategoryTypes();
    }
}
