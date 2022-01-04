package com.github.uyt.bl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.ReviewRepository;
import com.github.uyt.model.Review;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review getSingleReview(Long id) {
        return reviewRepository.getReviewById(id);
    }

    public List<Review> getRecipeReviews(Long recipeId) {
        return reviewRepository.getAllRecipeReviews(recipeId);
    }

    public Page<Review> getRecipeReviewsPaged(Pageable pageable, Long recipeId) {
        return reviewRepository.getRecipeReviewsPaged(pageable, recipeId);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
