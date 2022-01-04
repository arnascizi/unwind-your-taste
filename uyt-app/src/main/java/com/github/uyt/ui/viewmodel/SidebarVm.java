package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.enums.PageLocationEnum;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;

public class SidebarVm implements Serializable {
    private static final long serialVersionUID = 3950722338244936020L;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;
    @Getter private RecipePreviewView recommendedRecipe = new RecipePreviewView();

    @Init
    public void init(){
        List<RecipePreviewView> recipePreviewViews = recipeHelper.getRecommendedRecipes();
        recommendedRecipe = getRandomRecipe(recipePreviewViews);
    }

    @Command
    public void doSelect(String id) {
        Executions.sendRedirect(PageLocationEnum.COCKTAIL.getUrl() + "?id=" + id);
    }

    private RecipePreviewView getRandomRecipe(List<RecipePreviewView> recommendedRecipes) {
        Random rand = new Random();
        return recommendedRecipes.get(rand.nextInt(recommendedRecipes.size()));
    }
}
