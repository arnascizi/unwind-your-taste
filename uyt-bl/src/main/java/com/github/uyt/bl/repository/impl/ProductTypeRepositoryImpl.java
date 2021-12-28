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

import com.github.uyt.bl.repository.ProductTypeRepository;
import com.github.uyt.model.ProductType;
import com.github.uyt.model.ProductType_;

@Component
@Transactional
public class ProductTypeRepositoryImpl extends SimpleJpaRepository<ProductType, Long> implements ProductTypeRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public ProductTypeRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(ProductType.class, entityManager), entityManager);
    }

    @Override
    public ProductType findProductTypeByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductType> criteria = cb.createQuery(ProductType.class);
        Root<ProductType> root = criteria.from(ProductType.class);
        return em.createQuery(criteria.select(root).where(cb.equal(root.get(ProductType_.name), name))).getSingleResult();
    }
}
