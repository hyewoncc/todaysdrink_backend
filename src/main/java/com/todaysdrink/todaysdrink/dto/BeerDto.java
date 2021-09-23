package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import com.todaysdrink.todaysdrink.domain.Country;
import com.todaysdrink.todaysdrink.domain.LikeBeer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

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
    private int images;
    private Long beerLikeId;
    private Long beerLikes;

    public BeerDto(Beer beer) {
        id = beer.getId();
        name = beer.getName();
        country = beer.getCountry();
        alcohol = beer.getAlcohol();
        bitter = beer.getBitter();
        description = beer.getDescription();
        images = beer.getImages();
        beerLikeId = beer.getLike().getId();
        beerLikes = beer.getLike().getCount();
    }

    public BeerDto(Optional<Beer> beer) {
        id = beer.get().getId();
        name = beer.get().getName();
        country = beer.get().getCountry();
        alcohol = beer.get().getAlcohol();
        bitter = beer.get().getBitter();
        description = beer.get().getDescription();
        images = beer.get().getImages();
        beerLikeId = beer.get().getLike().getId();
        beerLikes = beer.get().getLike().getCount();
    }
}
