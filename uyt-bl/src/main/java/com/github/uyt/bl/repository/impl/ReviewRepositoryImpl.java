package com.github.uyt.bl.repository.impl;

@Component
@Transactional
public class ReviewRepositoryImpl extends SimpleJpaRepository<Review, Long> implements ReviewRepository{

    @PersistenceContext private EntityManager em;

    @Autowired
    public RecipeRepositoryImpl(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(eview.class, entityManager), entityManager);
    }

    @Override
    public List<Review> getAllUserReviews(@NonNull Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Review> criteria = cb.createQuery(Review.class);
        Root<Review> root = criteria.from(Review.class);
        criteria.where(cb.equal(root.get(Review_.USER_ID), userId))
        criteria.select(root);
        return em.createQuery(criteria).getResultList();
    }
}
