package com.github.uyt.ui.components;

import java.util.Locale;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.enums.DifficultyEnum;
import com.github.uyt.enums.PageLocationEnum;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.helper.ReviewHelper;
import com.github.uyt.ui.view.RecipePreviewView;
import com.github.uyt.ui.view.ReviewView;

import lombok.Getter;
import lombok.Setter;

public class RecipePreviewComponent extends HtmlMacroComponent {
    private static final long serialVersionUID = -4769859249736654113L;

    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;
    @WireVariable(rewireOnActivate = true) private transient ReviewHelper reviewHelper;

    @Getter @Setter private String recipeId;
    @Getter private RecipePreviewView model;
    @Getter private String difficultyColor;
    @Getter private Double rating;

    @Init
    public void init() {
        model = recipeHelper.getRecipePreview(Long.parseLong(recipeId));
        model.setEvaluationCount(reviewHelper.getRecipeReviews(model.getId()).size());
        rating = model.getEvaluationCount() > 0
                ? Double.valueOf(reviewHelper.getRecipeReviews(model.getId()).stream().map(ReviewView::getEvaluation).reduce(0, Integer::sum)) / model.getEvaluationCount()
                : 0;
        complexityValue();
    }

    @Command
    public void doSelect(String id) {
        Executions.sendRedirect(PageLocationEnum.COCKTAIL.getUrl() + "?id=" + id);
    }

    private String complexityValue() {
        if (model.getComplexity().toUpperCase(Locale.ROOT).equals(DifficultyEnum.EASY.getComplexity().toUpperCase(Locale.ROOT))) {
            difficultyColor = DifficultyEnum.EASY.getColor();
        } else if (model.getComplexity().toUpperCase(Locale.ROOT).equals(DifficultyEnum.MEDIUM.getComplexity().toUpperCase(Locale.ROOT))) {
            difficultyColor = DifficultyEnum.MEDIUM.getColor();
        } else if (model.getComplexity().toUpperCase(Locale.ROOT).equals(DifficultyEnum.HARD.getComplexity().toUpperCase(Locale.ROOT))) {
            difficultyColor = DifficultyEnum.HARD.getColor();
        }
        return difficultyColor;
    }
}
