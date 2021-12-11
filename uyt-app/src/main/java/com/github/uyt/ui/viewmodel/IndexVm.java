package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.CategoryView;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;
import lombok.Setter;

public class IndexVm implements Serializable {
    private static final long serialVersionUID = -68937799999085117L;

    @WireVariable(rewireOnActivate = true) private RecipeHelper recipeHelper;
    @Getter @Setter private RecipePreviewView recipePreviewView = new RecipePreviewView();
    @Getter private List<CategoryView> categories = new ArrayList<>();

    @Init
    private void init() {
        recipeHelper.getRecipes();;
    }
}
