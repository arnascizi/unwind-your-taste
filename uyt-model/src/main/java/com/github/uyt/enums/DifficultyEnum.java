package com.github.uyt.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DifficultyEnum {

    EASY("Lengvas","success"),
    MEDIUM("Vidutiniškas","warning"),
    HARD("Sudėtingas","danger");

    private final String complexity;
    private final String color;
}
