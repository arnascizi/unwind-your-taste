package com.github.uyt.enums;

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
    COCKTAIL_ENTRY("cocktailEntry"),
    SETTINGS("settings"),
    ABOUT("about");

    private final String translationValue;

    private static final List<PageTranslationEnum> TRANSLATION_ENUMS =
            Arrays.asList(COMMON, ERROR, LOGIN, REGISTER, COCKTAIL, COCKTAIL_ENTRY, ABOUT, SETTINGS);

    public static List<PageTranslationEnum> getTranslationEnums() {
        return TRANSLATION_ENUMS;
    }
}
