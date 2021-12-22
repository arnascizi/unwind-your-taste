package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;
import lombok.Setter;

public class SidebarVm implements Serializable {
    private static final long serialVersionUID = 3950722338244936020L;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;
    @Getter @Setter private List<RecipePreviewView> recommendedRecipes;

    @Init
    public void init(){
        recommendedRecipes = recipeHelper.getRecommendedRecipes();
    }

    @Command
    public void doSelect(String id) {
        Executions.sendRedirect("/cocktail?id=" + id);
    }
}