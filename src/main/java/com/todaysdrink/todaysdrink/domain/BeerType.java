package com.todaysdrink.todaysdrink.domain;

import java.util.Arrays;

public enum BeerType {
    ALE("에일"),
    IPA("IPA"),
    KOLSCH("쾰시"),
    PALE_LAGER("페일 라거"),
    LAGER("라거"),
    NON_ALCOHOL("논알콜"),
    PALE_ALE("페일 에일"),
    PILSENER("필스너"),
    RADLER("라들러"),
    STOUT("스타우트"),
    WEIZEN("밀맥주");

    private final String nickname;

    BeerType(final String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public static BeerType getBeerTypeByValue(String value) {
        return Arrays.stream(BeerType.values())
                .filter(beerType -> beerType.name().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
