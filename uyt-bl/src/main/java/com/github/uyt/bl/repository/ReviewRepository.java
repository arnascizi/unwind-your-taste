package com.github.uyt.bl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.Review;

@Transactional
@NoRepositoryBean
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getAllUserReviews(Long userId);

    List<Review> getAllRecipeReviews(Long recipeId);

    Page<Review> getRecipeReviewsPaged(Pageable pageable, Long recipeId);

    Review getReviewById(Long reviewId);
}
