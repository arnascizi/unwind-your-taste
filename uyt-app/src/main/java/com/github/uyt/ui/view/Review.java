package com.github.uyt.ui.view;

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
public class Review {

    private Long id;
    private UserView userView;
    private String comment;
    private double evaluation;
}
