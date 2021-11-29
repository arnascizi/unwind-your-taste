package com.github.uyt.ui.helper;

import org.springframework.stereotype.Component;

import com.github.uyt.bl.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewHelper {

    private final ReviewRepository reviewRepository;
}
