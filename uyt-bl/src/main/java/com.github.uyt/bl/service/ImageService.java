package com.github.uyt.bl.service;

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

import com.github.uyt.bl.exception.ImageNotFoundException;
import com.github.uyt.bl.repository.ImageRepository;
import com.github.uyt.bl.specification.ImageSpecifications;
import com.github.uyt.model.Image;
import com.github.uyt.model.Tag;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    private final EntityManager em;

    public Page<Image> findByName(String name, Pageable pageable) {
        return new PageImpl<>(imageRepository.findAll(ImageSpecifications.withName(name)), pageable, imageRepository.findAll(ImageSpecifications.withName(name)).size());
    }

    public Page<Image> findByTag(String name, Pageable pageable) {
        return new PageImpl<>(imageRepository.findAll(ImageSpecifications.withTag(name)), pageable, imageRepository.findAll(ImageSpecifications.withTag(name)).size());
    }

    public Page<Image> findImagesByNameOrTag(String name, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        Join<Image, Tag> tagJoin = root.join("tags", JoinType.LEFT);
        Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        Predicate tagsPredicate = cb.like(cb.lower(tagJoin.get("name").as(String.class)), "%" + name.toLowerCase() + "%");
        Predicate predicate = cb.or(namePredicate, tagsPredicate);
        cq.where(predicate).distinct(true);
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
