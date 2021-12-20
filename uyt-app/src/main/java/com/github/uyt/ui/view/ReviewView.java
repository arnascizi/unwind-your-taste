package com.github.uyt.ui.view;

import java.time.LocalDateTime;

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
public class ReviewView {

    private Long id;
    private String user;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long recipeId;
    private Integer evaluation;
}
