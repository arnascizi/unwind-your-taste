package com.github.uyt.ui.components;

import org.zkoss.zk.ui.HtmlMacroComponent;

import com.github.uyt.ui.view.RecipePreviewView;

import lombok.Getter;
import lombok.Setter;

public class RecipePreviewComponent extends HtmlMacroComponent {
    private static final long serialVersionUID = -8541924142904287852L;

    @Getter @Setter private RecipePreviewView model = new RecipePreviewView();
}
