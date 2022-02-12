package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BeerDto {
    private Long id;
    private String name;
    private String nickname;
    private String country;
    private String beerType;
    private double bitter;
    private double alcohol;
    private String description;
    private int images;
    private Long beerLikeId;
    private Long beerLikes;

    public BeerDto(Beer beer) {
        id = beer.getId();
        name = beer.getName();
        nickname = beer.getNickname();
        beerType = beer.getBeerType().getNickname();
        country = beer.getCountry().getNickname();
        alcohol = beer.getAlcohol();
        bitter = beer.getBitter();
        description = beer.getDescription();
        images = beer.getImages();
        beerLikeId = beer.getLike().getId();
        beerLikes = beer.getLike().getCount();
    }
}
