package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.Country;
import com.todaysdrink.todaysdrink.domain.LikeBeer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeerDto {
    private Long id;
    private String name;
    private Country country;
    private double alcohol;
    private int bitter;
    private String description;
    private Long likes;

    public BeerDto(Beer beer) {
        id = beer.getId();
        name = beer.getName();
        country = beer.getCountry();
        alcohol = beer.getAlcohol();
        bitter = beer.getBitter();
        description = beer.getDescription();
        likes = beer.getLike().getCount();
    }
}
