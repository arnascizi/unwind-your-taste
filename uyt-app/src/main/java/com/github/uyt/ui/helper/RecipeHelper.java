package com.github.uyt.ui.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.RecipeService;
import com.github.uyt.bl.service.UserAccountService;
import com.github.uyt.model.Product;
import com.github.uyt.model.Recipe;
import com.github.uyt.model.Search;
import com.github.uyt.ui.view.LoggedUser;
import com.github.uyt.ui.view.ProductView;
import com.github.uyt.ui.view.RecipePreviewView;
import com.github.uyt.ui.view.RecipeView;
import com.github.uyt.ui.view.SearchView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecipeHelper {

    private final RecipeService recipeService;
    private final UserAccountService userAccountService;

    public int getRecipeCount() {
        return recipeService.getAllRecipes().size();
    }

    public List<RecipeView> getAllRecipes(Pageable pageable) {
        ArrayList<RecipeView> list = new ArrayList<>();
        for (Recipe recipe : recipeService.finAllPageable(pageable)) {
            list.add(buildRecipeView(recipe));
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

    public List<RecipePreviewView> getSearchResultRecipePreview(Pageable pageable, SearchView searchView) {
        ArrayList<RecipePreviewView> list = new ArrayList<>();
        for (Recipe recipe : recipeService.fetchSearchResult(pageable, buildSearch(searchView))) {
            list.add(buildRecipePreview(recipe));
        }
        return list;
    }

    public RecipeView getDetailedRecipeView(Long id) {
        return buildRecipeView(recipeService.fetchSingleRecipe(id));
    }

    public List<RecipePreviewView> getRecommendedRecipes() {
        return recipeService.getRecommendedRecipes().stream().map(this::buildRecipePreview).collect(Collectors.toList());
    }

    public void saveRecipe(RecipeView recipeView, LoggedUser loggedUser) {
        recipeService.save(buildRecipe(recipeView, loggedUser));
    }

    private Recipe buildRecipe(RecipeView recipeView, LoggedUser loggedUser) {
        return Recipe.builder()
                .title(recipeView.getTitle())
                .preparationDescription(recipeView.getGuideline())
                .serving(recipeView.getServing())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .image(recipeView.getImage())
                .userAccount(userAccountService.getUserAccount(loggedUser.getUsername()))
                .build();
    }

    private Search buildSearch(SearchView searchView) {
        return Search.builder()
                .searchValue(searchView.getSearchValue())
                .categoryType(searchView.getCategoryType())
                .category(searchView.getCategory())
                .build();
    }

    private RecipePreviewView buildRecipePreview(Recipe recipe) {
        return RecipePreviewView.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .complexity(recipe.getComplexity().getValue())
                .thumbnail(recipe.getImage())
                .build();
    }

    private RecipeView buildRecipeView(Recipe recipe) {
        return RecipeView.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .guideline(recipe.getPreparationDescription())
                .serving(recipe.getServing())
                .uploadTime(recipe.getCreatedAt())
                .updateTime(recipe.getUpdatedAt())
                .uploader(recipe.getUserAccount().getUsername())
                .complexity(recipe.getComplexity().getValue())
                .category(recipe.getCocktailCategory().getName())
                .products(recipe.getProductList().stream().map(this::buildProductView).collect(Collectors.toList()))
                .image(recipe.getImage())
                .build();
    }

    private ProductView buildProductView(Product product) {
        return ProductView.builder()
                .id(product.getId())
                .name(product.getName())
                .productType(product.getProductType().getName())
                .measurement(product.getMeasurement().getName())
                .build();
    }

    public byte[] createThumbnail(byte[] image, String fileType) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(image); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            BufferedImage thumbnail = Scalr.resize(bufferedImage, 150);
            ImageIO.write(thumbnail, fileType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
