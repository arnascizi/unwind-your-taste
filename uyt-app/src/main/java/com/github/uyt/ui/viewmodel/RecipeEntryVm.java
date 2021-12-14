package com.github.uyt.ui.viewmodel;

import java.io.Serializable;

import javax.print.attribute.standard.Media;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.view.RecipeView;

import lombok.Getter;

public class RecipeEntryVm implements Serializable {
    private static final long serialVersionUID= -1813920837374820512L;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;
    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;

    @Getter private RecipeView recipeView;

    @Init
    public void init() {

    }

    @Command
    public void doUpload(@BindingParam("image") Media media) {

    }
}
