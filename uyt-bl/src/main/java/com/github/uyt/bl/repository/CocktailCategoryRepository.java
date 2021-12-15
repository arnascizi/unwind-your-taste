package com.github.uyt.bl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.CategoryType;
import com.github.uyt.model.CocktailCategory;

@Transactional
@NoRepositoryBean
public interface CocktailCategoryRepository extends JpaRepository<CocktailCategory, Long> {

    List<CocktailCategory> fetchCocktailCategoriesByType(String categoryType);

    List<CocktailCategory> fetchAllCategories();

    List<CategoryType> fetchAllCategoryTypes();
}
