package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.enums.PageLocationEnum;
import com.github.uyt.model.CommonConstants;
import com.github.uyt.ui.helper.AccountHelper;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.helper.ReviewHelper;
import com.github.uyt.ui.utility.SecurityFunctions;
import com.github.uyt.ui.view.RecipeView;
import com.github.uyt.ui.view.ReviewView;

import lombok.Getter;
import lombok.Setter;

public class CocktailVm implements Serializable {
    private static final long serialVersionUID = 7115882667391182437L;

    private static final int PAGE_SIZE = 5;

    @WireVariable(rewireOnActivate = true) private transient AccountHelper accountHelper;
    @WireVariable(rewireOnActivate = true) private transient RecipeHelper recipeHelper;
    @WireVariable(rewireOnActivate = true) private transient ReviewHelper reviewHelper;

    @Getter @Setter private RecipeView model = new RecipeView();
    @Getter @Setter private ReviewView review = new ReviewView();
    @Getter @Setter private double totalRating;
    @Getter @Setter private int currentPage;
    @Getter @Setter private int ratingCount;
    @Getter private List<ReviewView> reviews;

    @Init
    @NotifyChange({"model", "review", "totalRating", "reviews"})
    public void init(@QueryParam("id") String id) {
        model = recipeHelper.getDetailedRecipeView(Long.parseLong(id));
        loadReviews(model.getId());
        ratingCount = reviews.size();
        totalRating = CommonConstants.ZERO;
        review.setRecipeId(Long.parseLong(id));
    }

    @Command
    @NotifyChange({"model", "reviews", "totalRating", "ratingCount"})
    public void doSubmitReview() {
        if (review.getEvaluation() == null) {
            review.setEvaluation(CommonConstants.ZERO);
        }
        review.setUser(Objects.requireNonNull(SecurityFunctions.getLoggedUser()).getUsername());
        reviewHelper.saveReview(review);
        ratingCount = reviews.size();
        loadReviews(model.getId());
    }

    @Command
    @NotifyChange({"currentPage", "reviews"})
    public void doPaging() {
        Pageable pageable = PageRequest.of(currentPage, getPageSize());
        reviews = reviewHelper.getReviewsPageable(pageable, model.getId());
    }

    @Command
    public void doDelete() {
        recipeHelper.deleteRecipe(model.getId());
        Executions.sendRedirect(PageLocationEnum.COCKTAILS.getUrl());
    }

    public String getLoggedUserUsername() {
        return Objects.requireNonNull(SecurityFunctions.getLoggedUser()).getUsername();
    }

    public boolean isUserLogged() {
        return SecurityFunctions.getLoggedUser() != null;
    }

    private void loadReviews(Long id) {
        Pageable pageable = PageRequest.of(currentPage, getPageSize());
        reviews = reviewHelper.getReviewsPageable(pageable, id);
    }

    public int getPageSize() {
        return PAGE_SIZE;
    }

    public boolean isAbleToModify() {
        return SecurityFunctions.isUserLogged() && model.getUploader().equals(Objects.requireNonNull(SecurityFunctions.getLoggedUser()).getUsername());
    }
}
