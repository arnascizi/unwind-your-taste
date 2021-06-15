package lt.insoft.gallery.bl.specification;

import java.awt.print.Pageable;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import lt.insoft.gallery.model.Image;
import lt.insoft.gallery.model.Tag;

public class ImageSpecifications {

    public static Specification<Image> withName(String name) {
        if (name == null) {
            return null;
        }
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }

    public static Specification<Image> withTag(String name) {
        if (name == null) {
            return null;
        }
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<Image, Tag> tagJoin = root.join("tags", JoinType.LEFT);
            return criteriaBuilder.like(criteriaBuilder.lower(tagJoin.get("name").as(String.class)), "%" + name + "%");
        });
    }

    public static Specification<Image> withNameOrTag(String name) {
        if (name == null) {
            return null;
        }
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<Image, Tag> tagJoin = root.join("tags", JoinType.LEFT);
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name").as(String.class)), "%" + name + "%");
            Predicate tagPredicate = criteriaBuilder.like(criteriaBuilder.lower(tagJoin.get("name").as(String.class)), "%" + name + "%");
            return criteriaBuilder.or(namePredicate, tagPredicate);
        });
    }
}
