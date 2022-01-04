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
public class RecipePreviewView implements Serializable {

    private Long id;
    private String title;
    private byte[] thumbnail;
    private String complexity;
    private Integer evaluationCount;
    private Double rating;
}
