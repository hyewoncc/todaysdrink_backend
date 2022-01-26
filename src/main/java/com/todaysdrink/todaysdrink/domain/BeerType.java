package com.todaysdrink.todaysdrink.domain;

import java.util.Arrays;

public enum BeerType {
    LAGER("라거"),
    IPA("IPA"),
    PALE_ALE("페일 에일"),
    STOUT("스타우트"),
    WEIZEN("밀맥주"),
    PILSENER("필스너"),
    NON_ALCOHOL("논알콜");

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
