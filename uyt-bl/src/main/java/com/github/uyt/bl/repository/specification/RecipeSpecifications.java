package com.github.uyt.bl.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.github.uyt.model.Recipe;
import com.github.uyt.model.Recipe_;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecipeSpecifications {

    public static Specification<Recipe> withId(Long id) {
        if (id == null) {
            return null;
        }
        return ((root, query, cb) -> cb.equal(root.get(Recipe_.ID), id));
    }
}
