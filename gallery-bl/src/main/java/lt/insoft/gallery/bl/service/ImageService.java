package lt.insoft.gallery.bl.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.exception.ImageNotFoundException;
import lt.insoft.gallery.bl.repository.ImageRepository;
import lt.insoft.gallery.model.Image;
import lt.insoft.gallery.model.Tag;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    private final EntityManager em;

    private CriteriaBuilder cb;

    public Page<Image> findImagesByNameOrTag(String name, Pageable pageable) {
        cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        Join<Image, Tag> tagJoin = root.join("tags", JoinType.LEFT);
        Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        Predicate tagsPredicate = cb.like(cb.lower(tagJoin.get("name").as(String.class)), "%" + name.toLowerCase() + "%");
        Predicate predicate = cb.or(namePredicate, tagsPredicate);
        return getImages(pageable, cb, cq, root, predicate);
    }

    public Page<Image> findImagesByTag(String name, Pageable pageable) {
        cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        Join<Image, Tag> tagJoin = root.join("tags", JoinType.LEFT);
        Predicate tagsPredicate = cb.like(cb.lower(tagJoin.get("name").as(String.class)), "%" + name.toLowerCase() + "%");
        return getImages(pageable, cb, cq, root, tagsPredicate);
    }

    public Page<Image> findImagesByName(String name, Pageable pageable) {
        cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        return getImages(pageable, cb, cq, root, namePredicate);
    }

    private Page<Image> getImages(Pageable pageable, CriteriaBuilder cb, CriteriaQuery<Image> cq, Root<Image> root, Predicate tagsPredicate) {
        cq.where(tagsPredicate).distinct(true);
        cb.asc(root.get("name"));
        TypedQuery<Image> query = em.createQuery(cq);
        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }

    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }

    public Image fetchImage(Long id) throws ImageNotFoundException {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    public void removeImage(Long id) throws ImageNotFoundException {
        Image image = fetchImage(id);
        imageRepository.delete(image);
    }

    public Iterable<Image> getPageable(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }


}
