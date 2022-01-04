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

import com.github.uyt.bl.repository.UserRoleRepository;
import com.github.uyt.model.UserRole;

import lombok.NonNull;

@Component
@Transactional
public class UserRoleRepositoryImpl extends SimpleJpaRepository<UserRole, Long> implements UserRoleRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public UserRoleRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserRole.class, entityManager), entityManager);
    }

    @Override
    public UserRole findUserRoleByValue(@NonNull String userRole) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserRole> criteria = cb.createQuery(UserRole.class);
        Root<UserRole> root = criteria.from(UserRole.class);

        // criteria.where(cb.equal(root.get(UserRole_.VALUE), userRole.toUpperCase(Locale.ROOT)));
        return em.createQuery(criteria).getSingleResult();
    }
}
