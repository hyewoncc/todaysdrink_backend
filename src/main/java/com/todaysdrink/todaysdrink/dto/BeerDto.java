package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
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
    private BeerType beerType;
    private int bitter;
    private double alcohol;
    private String description;
    private Long beerLikeId;
    private Long beerLikes;

    public BeerDto(Beer beer) {
        id = beer.getId();
        name = beer.getName();
        country = beer.getCountry();
        alcohol = beer.getAlcohol();
        bitter = beer.getBitter();
        description = beer.getDescription();
        beerLikeId = beer.getLike().getId();
        beerLikes = beer.getLike().getCount();
    }
}
