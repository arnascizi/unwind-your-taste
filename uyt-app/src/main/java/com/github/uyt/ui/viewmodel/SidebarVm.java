package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;

public class SidebarVm implements Serializable {
    private static final long serialVersionUID = 3950722338244936020L;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;
    @Getter private List<RecipePreviewView> recommendedRecipes;

    @Init
    public void init(){
        recommendedRecipes = recipeHelper.getRecommendedRecipes();
    }
}
