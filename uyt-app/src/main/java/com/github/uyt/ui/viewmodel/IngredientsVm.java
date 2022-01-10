package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.CommonAttributesHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.ProductView;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;
import lombok.Setter;

public class IngredientsVm implements Serializable {
    private static final long serialVersionUID = 4919743721897832966L;

    @WireVariable(rewireOnActivate = true) private transient CommonAttributesHelper commonAttributesHelper;
    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter @Setter private List<RecipePreviewView> recipes = new ArrayList<>();
    @Getter @Setter private List<ProductView> ingredients = new ArrayList<>();
    @Getter @Setter private Long someId;

    @Init
    public void init() {
        ingredients = commonAttributesHelper.getAllIngredients();
    }

    @Command
    @NotifyChange({"ingredients", "recipes", "someId"})
    public void doFillRecipes(String id) {
        System.out.println(id);
        someId = Long.parseLong(id);
        if (!recipes.isEmpty()) {
            recipes.clear();
        }

        recipes = recipeHelper.getRecipesByProduct(Long.parseLong(id));
        ingredients = commonAttributesHelper.getAllIngredients();
    }
}
