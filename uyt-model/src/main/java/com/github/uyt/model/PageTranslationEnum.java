package com.github.uyt.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PageTranslationEnum {
    COMMON("common"),
    ERROR("error"),
    LOGIN("login"),
    REGISTER("register"),
    COCKTAIL("cocktail"),
    ABOUT("about");

    private final String translationValue;

    private static final List<PageTranslationEnum> TRANSLATION_ENUMS =
            Arrays.asList(COMMON, ERROR, LOGIN, REGISTER, COCKTAIL, ABOUT);

    public static List<PageTranslationEnum> getTranslationEnums() {
        return TRANSLATION_ENUMS;
    }
}
