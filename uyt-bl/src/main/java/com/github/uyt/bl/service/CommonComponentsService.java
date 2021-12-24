package com.github.uyt.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.CocktailCategoryRepository;
import com.github.uyt.bl.repository.ComplexityRepository;
import com.github.uyt.model.CategoryType;
import com.github.uyt.model.CocktailCategory;
import com.github.uyt.model.Complexity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonComponentsService {

    private final CocktailCategoryRepository cocktailCategoryRepository;
    private final ComplexityRepository complexityRepository;

    public List<CocktailCategory> fetchCocktailCategoriesByType(String categoryType) {
        return cocktailCategoryRepository.fetchCocktailCategoriesByType(categoryType);
    }

    public List<CocktailCategory> fetchAllCategories() {
        return cocktailCategoryRepository.fetchAllCategories();
    }

    public List<CategoryType> fetchAllCategoryTypes(){
        return cocktailCategoryRepository.fetchAllCategoryTypes();
    }

    public List<Complexity> fetchAllComplexities() {
        return complexityRepository.fetchAllComplexities();
    }
}
