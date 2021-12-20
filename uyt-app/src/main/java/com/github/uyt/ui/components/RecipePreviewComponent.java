package com.github.uyt.ui.components;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;
import lombok.Setter;

public class RecipePreviewComponent extends HtmlMacroComponent {
    private final static long serialVersionUID = -4769859249736654113L;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter @Setter private String recipeId;
    @Getter private RecipePreviewView model;

    @Init
    public void init() {
        model = recipeHelper.getRecipePreview(Long.parseLong(recipeId));
    }

    @Command
    public void doSelect(String id) {
        Executions.sendRedirect("/cocktail?id=" + id);
    }
}
