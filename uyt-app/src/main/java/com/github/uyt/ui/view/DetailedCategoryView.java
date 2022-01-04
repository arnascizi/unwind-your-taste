package com.github.uyt.ui.view;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailedCategoryView implements Serializable {
    private static final long serialVersionUID = 1176811294616085336L;

    private Long id;
    private String title;
    private String categoryType;
}
