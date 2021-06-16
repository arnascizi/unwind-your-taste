package lt.insoft.gallery.bl.specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

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
}
