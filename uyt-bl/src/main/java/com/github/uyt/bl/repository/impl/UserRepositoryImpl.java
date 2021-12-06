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

import com.github.uyt.bl.repository.UserRepository;
import com.github.uyt.model.UserAccount;
import com.github.uyt.model.UserAccount_;

import lombok.NonNull;

@Component
@Transactional
public class UserRepositoryImpl extends SimpleJpaRepository<UserAccount, Long> implements UserRepository {

    @PersistenceContext private EntityManager em;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(UserAccount.class, entityManager), entityManager);
    }

    @Override
    public UserAccount findByUsername(@NonNull String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserAccount> criteria = cb.createQuery(UserAccount.class);
        Root<UserAccount> root = criteria.from(UserAccount.class);
        cb.equal(root.get(UserAccount_.USERNAME), username);
        return em.createQuery(criteria).getSingleResult();
    }
}
