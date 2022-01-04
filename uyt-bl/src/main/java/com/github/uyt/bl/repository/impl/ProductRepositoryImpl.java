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

import com.github.uyt.bl.repository.ProductRepository;
import com.github.uyt.model.Product;

@Component
@Transactional
public class ProductRepositoryImpl extends SimpleJpaRepository<Product, Long> implements ProductRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(Product.class, entityManager), entityManager);
    }

    @Override
    public List<Product> getAllProducts() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }
}
