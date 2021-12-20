package com.github.uyt.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.github.uyt.model.CommonConstants;
import com.github.uyt.ui.helper.RecipeHelper;
import com.github.uyt.ui.helper.ReviewHelper;
import com.github.uyt.ui.view.RecipeView;
import com.github.uyt.ui.view.ReviewView;

import lombok.Getter;
import lombok.Setter;

public class CocktailVm implements Serializable {
    private static final long serialVersionUID = 7115882667391182437L;

    private static final int PAGE_SIZE = 5;

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
        Executions.getCurrent().getParameter("id");
        model = recipeHelper.getDetailedRecipeView(Long.parseLong(id));
        loadReviews(model.getId());
        ratingCount = reviews.size();
        totalRating = 0;
        review.setRecipeId(Long.parseLong(id));
    }

    @Command
    @NotifyChange({"model", "reviews", "totalRating", "ratingCount"})
    public void doSubmitReview() {
        if (!isValid()) {
            return;
        }
        review.setUser("arnas");
        reviewHelper.saveReview(review);
        // totalRating = calculateTotalRating();
        ratingCount = reviews.size();
        loadReviews(model.getId());
    }

    @Command
    public void doTest() {
        System.out.println(review.getEvaluation());
    }

    @Command
    @NotifyChange({"reviews", "review", "model", "totalRating", "ratingCount"})
    public void doEdit(String reviewId) {
        // reviewHelper.deleteReview(Long.parseLong(reviewId));
        // loadReviews(model.getId());
        // totalRating = calculateTotalRating();
        // ratingCount = reviews.size();
    }

    @Command
    @NotifyChange({"currentPage", "reviews"})
    public void doPaging() {
        Pageable pageable = PageRequest.of(currentPage, getPageSize());
        reviews = reviewHelper.getReviewsPageable(pageable, model.getId());
    }

    private double calculateTotalRating() {
        return reviews.size() != 0
                ? getReviews().stream().map(ReviewView::getEvaluation).reduce(CommonConstants.ZERO, Integer::sum) / reviews.size()
                : 0;
    }

    private void loadReviews(Long id) {
        Pageable pageable = PageRequest.of(currentPage, getPageSize());
        reviews = reviewHelper.getReviewsPageable(pageable, id);
    }

    public int getPageSize() {
        return PAGE_SIZE;
    }

    private boolean isValid() {
        return review.getEvaluation() < 1;
    }
}
