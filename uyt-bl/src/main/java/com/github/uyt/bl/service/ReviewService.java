package com.github.uyt.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.ReviewRepository;
import com.github.uyt.model.Review;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getRecipeReviews(Long recipeId) {
        return reviewRepository.findAll();
    }
}
