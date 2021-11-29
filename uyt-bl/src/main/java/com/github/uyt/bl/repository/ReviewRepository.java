package com.github.uyt.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.uyt.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
