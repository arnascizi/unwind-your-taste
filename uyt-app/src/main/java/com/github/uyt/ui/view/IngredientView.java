package com.github.uyt.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientView implements Serializable {
    private static final long serialVersionUID = -6227829755806904251L;

    private Long id;
    private String name;
}
