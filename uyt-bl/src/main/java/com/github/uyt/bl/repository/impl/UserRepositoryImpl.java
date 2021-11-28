// // package com.github.uyt.bl.repository.impl;
//
// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import javax.persistence.criteria.CriteriaBuilder;
// import javax.persistence.criteria.CriteriaQuery;
// import javax.persistence.criteria.Root;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
// import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.github.uyt.bl.repository.UserRepository;
// import com.github.uyt.model.UserAccount;
// import com.github.uyt.model.UserAccount_;
//
// // @Component
// // @Transactional
// // public class UserRepositoryImpl extends SimpleJpaRepository<UserAccount, Long> implements UserRepository {
// //
// //     @PersistenceContext private EntityManager em;
// //
// //     @Autowired
// //     public UserRepositoryImpl(EntityManager entityManager) {
// //         super(JpaEntityInformationSupport.getEntityInformation(UserAccount.class, entityManager), entityManager);
// //     }
// //
// //     @Override
// //     public UserAccount getUserByUsername(String username) {
// //         CriteriaBuilder cb = em.getCriteriaBuilder();
// //         CriteriaQuery<UserAccount> criteria = cb.createQuery(UserAccount.class);
// //         Root<UserAccount> root = criteria.from(UserAccount.class);
// //         criteria.where(cb.equal(root.get(UserAccount_.username), username));
// //         return em.createQuery(criteria).getSingleResult();
// //     }
// // }
