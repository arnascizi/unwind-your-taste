package com.github.uyt.bl.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.ReviewRepository;
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
}
