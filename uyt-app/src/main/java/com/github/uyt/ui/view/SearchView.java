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
public class SearchView implements Serializable {
    private static final long serialVersionUID = -3340022872644016648L;

    private String searchValue;
    private String category;
    private String categoryType;
}
