package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;
import lombok.Setter;

public class CocktailsVm implements Serializable {
    private static final long serialVersionUID = -2199803193269955145L;

    private static final int PAGE_SIZE = 8;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter @Setter private List<RecipePreviewView> recipeList;
    @Getter @Setter private int currentPage;
    @Getter private int maxPages;

    @Init
    public void init() {
        currentPage = 0;
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        recipeList = recipeHelper.getAllRecipesPreview(pageable);
        maxPages = recipeHelper.getRecipeCount();
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

    public static int getPageSize() {
        return PAGE_SIZE;
    }
}
