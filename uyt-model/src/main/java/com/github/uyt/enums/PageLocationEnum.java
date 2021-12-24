package com.github.uyt.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PageLocationEnum {

    ABOUT("/about"),
    COCKTAIL("/cocktail"),
    COCKTAIL_ENTRY("/createCocktail"),
    COCKTAILS("/cocktails"),
    ERROR("/error"),
    INDEX("/index"),
    INGREDIENTS("/ingredients"),
    LOGIN("/login"),
    REGISTER("/register");

    private final String url;

    private static final List<PageLocationEnum> FULL_PAGES =
            Arrays.asList(COCKTAIL, COCKTAIL_ENTRY, COCKTAILS, ERROR, LOGIN, REGISTER);

    private static final List<PageLocationEnum> SIDEBAR_PAGES =
            Arrays.asList(ABOUT, INDEX, INGREDIENTS);

    public static List<PageLocationEnum> getFullPagesEnums() {
        return FULL_PAGES;
    }

    public static List<PageLocationEnum> getSideBarPagesEnums() {
        return SIDEBAR_PAGES;
    }
}
