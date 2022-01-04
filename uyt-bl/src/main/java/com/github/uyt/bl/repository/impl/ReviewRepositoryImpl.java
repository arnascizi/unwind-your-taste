package com.github.uyt.bl.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.ReviewRepository;
import com.github.uyt.model.Recipe_;
import com.github.uyt.model.Review;
import com.github.uyt.model.Review_;
import com.github.uyt.model.UserAccount_;

import lombok.NonNull;

@Component
@Transactional
public class ReviewRepositoryImpl extends SimpleJpaRepository<Review, Long> implements ReviewRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public ReviewRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(Review.class, entityManager), entityManager);
    }

    @Override
    public List<Review> getAllUserReviews(@NonNull Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Review> criteria = cb.createQuery(Review.class);
        Root<Review> root = criteria.from(Review.class);
        criteria.where(cb.equal(root.get(Review_.userAccount).get(UserAccount_.id), userId));
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public List<Review> getAllRecipeReviews(@NonNull Long recipeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Review> criteria = cb.createQuery(Review.class);
        Root<Review> root = criteria.from(Review.class);
        criteria.where(cb.equal(root.get(Review_.recipe).get(Recipe_.id), recipeId));
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public Page<Review> getRecipeReviewsPaged(@NonNull Pageable pageable, @NonNull Long recipeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Review> criteria = cb.createQuery(Review.class);
        Root<Review> root = criteria.from(Review.class);
        criteria.select(root).where(cb.equal(root.get(Review_.recipe).get(Recipe_.id), recipeId));
        TypedQuery<Review> query = em.createQuery(criteria);
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }

    @Override
    public Review getReviewById(@NonNull Long reviewId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Review> criteria = cb.createQuery(Review.class);
        Root<Review> root = criteria.from(Review.class);
        criteria.select(root).where(cb.equal(root.get(Review_.id), reviewId));
        return em.createQuery(criteria).getSingleResult();
    }
}
