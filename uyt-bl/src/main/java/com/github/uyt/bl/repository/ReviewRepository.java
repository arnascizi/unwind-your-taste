package com.github.uyt.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.uyt.model.Review;

@Transactional
@NoRepositoryBean
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getAllUserReviews(Long userId);
}
