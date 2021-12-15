package com.github.uyt.ui.view;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryTypeView implements Serializable {
    private static final long serialVersionUID = -3701874808435229468L;

    private Long id;
    private String type;
}
