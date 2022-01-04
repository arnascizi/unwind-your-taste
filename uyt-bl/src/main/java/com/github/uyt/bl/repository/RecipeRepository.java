package com.github.uyt.bl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.Recipe;
import com.github.uyt.model.Search;

@NoRepositoryBean
@Transactional
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

   Page<Recipe> fetchAllRecipes(Pageable pageable);

   Recipe fetchSingleRecipe(Long id);

   List<Recipe> fetchLatestRecipes();

   List<Recipe> fetchRandomRecipes();

   Page<Recipe> fetchSearchResult(Pageable pageable, Search search);

   List<Recipe> getRecipeByProduct(Long productId);
}
