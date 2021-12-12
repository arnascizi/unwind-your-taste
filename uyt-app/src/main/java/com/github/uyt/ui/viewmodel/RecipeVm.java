package com.github.uyt.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipeView;

import lombok.Getter;
import lombok.Setter;

public class RecipeVm implements Serializable {
    private static final long serialVersionUID = 7115882667391182437L;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter @Setter private RecipeView model = new RecipeView();

    @Init
    public void init(@QueryParam("id") String id) {
        Executions.getCurrent().getParameter("id");
        model = recipeHelper.getDetailedRecipeView(Long.parseLong(id));
    }
}
