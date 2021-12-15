package com.todaysdrink.todaysdrink.domain;

import java.util.Arrays;

public enum Country {
    KOR("KOR"),
    JPN("JPN"),
    CHN("CHN"),
    DEU("DEU");

    private final String name;

    Country(final String name) {
        this.name = name;
    }

    public static Country getCountryByValue(String value) {
        return Arrays.stream(Country.values())
                .filter(country -> country.name.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
