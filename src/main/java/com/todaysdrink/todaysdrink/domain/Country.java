package com.todaysdrink.todaysdrink.domain;

import java.util.Arrays;

public enum Country {
    ARG("아르헨티나"),
    CHN("중국"),
    DEU("독일"),
    DNK("덴마크"),
    GBR("영국"),
    JPN("일본"),
    KOR("대한민국"),
    NLD("네덜란드"),
    PHL("필리핀"),
    SGP("싱가포르"),
    THA("태국"),
    TWN("대만"),
    USA("미국");

    private final String nickname;

    Country(final String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public static Country getCountryByValue(String value) {
        return Arrays.stream(Country.values())
                .filter(country -> country.name().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
