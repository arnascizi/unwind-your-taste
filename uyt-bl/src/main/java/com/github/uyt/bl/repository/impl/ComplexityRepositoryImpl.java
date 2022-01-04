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

import com.github.uyt.bl.repository.ComplexityRepository;
import com.github.uyt.model.Complexity;

@Component
@Transactional
public class ComplexityRepositoryImpl extends SimpleJpaRepository<Complexity, Long> implements ComplexityRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public ComplexityRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(Complexity.class, entityManager), entityManager);
    }

    @Override
    public List<Complexity> fetchAllComplexities() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Complexity> criteria = cb.createQuery(Complexity.class);
        Root<Complexity> root = criteria.from(Complexity.class);
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }
}
