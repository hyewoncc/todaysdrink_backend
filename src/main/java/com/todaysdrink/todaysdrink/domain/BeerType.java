package com.todaysdrink.todaysdrink.domain;

import java.util.Arrays;

public enum BeerType {
    LAGER("LAGER"),
    IPA("IPA"),
    PALE_ALE("PALE_ALE"),
    STOUT("STOUT"),
    WEIZEN("WEIZEN"),
    PILSENER("PILSERNER"),
    NON_ALCOHOL("NON_ALCOHOL");

    private final String name;

    BeerType(final String name) {
        this.name = name;
    }

    public static BeerType getBeerTypeByValue(String value) {
        return Arrays.stream(BeerType.values())
                .filter(beerType -> beerType.name.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
