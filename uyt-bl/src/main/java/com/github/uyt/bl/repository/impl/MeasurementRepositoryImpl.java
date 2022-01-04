package com.github.uyt.bl.repository.impl;

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

import com.github.uyt.bl.repository.MeasurementRepository;
import com.github.uyt.model.Measurement;
import com.github.uyt.model.Measurement_;

import lombok.NonNull;

@Component
@Transactional
public class MeasurementRepositoryImpl extends SimpleJpaRepository<Measurement, Long> implements MeasurementRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public MeasurementRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(Measurement.class, entityManager), entityManager);
    }

    @Override
    public Measurement findMeasurementByName(@NonNull String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Measurement> criteria = cb.createQuery(Measurement.class);
        Root<Measurement> root = criteria.from(Measurement.class);
        return em.createQuery(criteria.select(root).where(cb.equal(root.get(Measurement_.value), name))).getSingleResult();
    }
}
