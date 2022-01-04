package com.github.uyt.model;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonConstants {

    public static final Locale LOCALE_LT = new Locale("lt", "LT");
    public static final Locale LOCALE_EN = Locale.ENGLISH;

    public static LocalTime MORNING = LocalTime.parse("06:00:00.00");
    public static LocalTime EVENING = LocalTime.parse("18:00:00.00");

    public static final String ALKOHOLIC = "Alkoholiniai";
    public static final String NON_ALCOHOLIC = "Nealkoholiniai";

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static final int ZERO = 0;
}
