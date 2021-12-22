package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.enums.PageLocationEnum;
import com.github.uyt.ui.helper.CommonAttributesHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.CategoryView;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;

public class IndexVm implements Serializable {
    private static final long serialVersionUID = -68937799999085117L;

    @WireVariable(rewireOnActivate = true) private transient CommonAttributesHelper commonAttributesHelper;
    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter private List<CategoryView> categories;
    @Getter private List<RecipePreviewView> latestRecipes;

    @Init
    private void init() {
        categories = commonAttributesHelper.getCocktailCategories().stream().limit(4).collect(Collectors.toList());
        latestRecipes = recipeHelper.getRecommendedRecipes().stream().limit(4).collect(Collectors.toList());
    }

    @Command
    public void doCheckAll() {
        Executions.sendRedirect(PageLocationEnum.COCKTAILS.getUrl());
    }
}
