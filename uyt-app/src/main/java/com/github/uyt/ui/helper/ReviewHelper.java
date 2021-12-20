package com.github.uyt.ui.helper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.RecipeService;
import com.github.uyt.bl.service.ReviewService;
import com.github.uyt.bl.service.UserAccountService;
import com.github.uyt.model.Review;
import com.github.uyt.ui.view.ReviewView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewHelper {

    private final ReviewService reviewService;
    private final RecipeService recipeService;
    private final UserAccountService userAccountService;

    public ReviewView getSingleReview(Long reviewId) {
        return buildReviewView(reviewService.getSingleReview(reviewId));
    }

    public void saveReview(ReviewView reviewView) {
        reviewService.saveReview(buildReview(reviewView));
    }

    public List<ReviewView> getRecipeReviews(Long recipeId) {
        return reviewService.getRecipeReviews(recipeId).stream().map(this::buildReviewView).collect(Collectors.toList());
    }

    public List<ReviewView> getReviewsPageable(Pageable pageable, Long recipeId) {
        return reviewService.getRecipeReviewsPaged(pageable, recipeId).stream().map(this::buildReviewView).collect(Collectors.toList());
    }

    public void deleteReview(Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    private ReviewView buildReviewView(Review review) {
        return ReviewView.builder()
                .id(review.getId())
                .comment(review.getComment())
                .evaluation(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .user(userAccountService.getUsernameById(review.getUserAccount().getId()))
                .recipeId(review.getRecipe().getId())
                .build();
    }

    private Review buildReview(ReviewView view) {
        return Review.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .comment(view.getComment())
                .rating(view.getEvaluation())
                .userAccount(userAccountService.getUserAccount(view.getUser()))
                .recipe(recipeService.fetchSingleRecipe(view.getRecipeId()))
                .build();
    }
}
