package com.github.uyt.ui.view;

import java.util.List;

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
public class RecipeView {

    private long id;
    private String title;
    private byte[] image;
    private String guideline;
    private String serving;
    private String uploadTime;
    private String updateTime;
    private String uploader;
    private List<ReviewView> reviews;
    private List<CompositionView> products;
    private ComplexityView complexity;
    private String category;
    private CategoryView categoryView;
}
