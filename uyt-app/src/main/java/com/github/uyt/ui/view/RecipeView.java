package com.github.uyt.ui.view;

import java.time.LocalDateTime;
import java.util.List;

import com.github.uyt.model.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeView {

    private long id;
    private String title;
    private byte[] image;
    private String guideline;
    private String serving;
    private LocalDateTime uploadTime;
    private LocalDateTime updateTime;
    private String uploader;
    private List<Review> reviews;
    private String complexity;
    private String category;
}
