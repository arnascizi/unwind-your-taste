package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.ComonAttributesHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.CategoryTypeView;
import com.github.uyt.ui.view.CategoryView;
import com.github.uyt.ui.view.RecipePreviewView;
import com.github.uyt.ui.view.SearchView;

import lombok.Getter;
import lombok.Setter;

public class CocktailsVm implements Serializable {
    private static final long serialVersionUID = -2199803193269955145L;

    private static final int PAGE_SIZE = 8;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;
    @WireVariable(rewireOnActivate = true) private transient ComonAttributesHelper comonAttributesHelper;

    @Getter @Setter private List<RecipePreviewView> recipeList;
    @Getter @Setter private SearchView search = new SearchView();
    @Getter @Setter private int currentPage;
    @Getter private List<CategoryTypeView> categoryTypeViews;
    @Getter private List<CategoryView> cocktailCategories;
    @Getter private int maxPages;

    @Init
    public void init(@QueryParam("searchValue") String searchValue) {
        search.setSearchValue(searchValue);
        categoryTypeViews = comonAttributesHelper.getAllCategoryTypes();
        search.setCategoryType(categoryTypeViews.get(1).getType());
        currentPage = 0;
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);

        recipeList = search.getSearchValue() != null
                ? recipeHelper.getSearchResultRecipePreview(pageable, search)
                : recipeHelper.getAllRecipesPreview(pageable);

        maxPages = recipeHelper.getRecipeCount();
        cocktailCategories = comonAttributesHelper.getCocktailCategories(search.getCategoryType());
    }

    @Command
    public void doSelect(String id) {
        Executions.sendRedirect("/cocktail?id=" + id);
    }

    @Command
    @NotifyChange({"currentPage", "recipeList"})
    public void doPaging() {
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        recipeList = recipeHelper.getAllRecipesPreview(pageable);
    }

    @Command
    @NotifyChange({"categoryTypeViews", "search", "cocktailCategories"})
    public void doChangeSelection() {
        cocktailCategories = comonAttributesHelper.getCocktailCategories(search.getCategoryType());
    }

    @Command
    @NotifyChange({"categoryTypeViews", "search", "cocktailCategories", "recipeList", "maxPages"})
    public void doSearch() {
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        maxPages = recipeHelper.getAllRecipesPreview(pageable).size();
        recipeList = recipeHelper.getSearchResultRecipePreview(pageable, search);
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }
}
