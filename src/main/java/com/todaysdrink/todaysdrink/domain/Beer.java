package com.todaysdrink.todaysdrink.domain;

import com.todaysdrink.todaysdrink.dto.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue
    @Column(name = "BEER_ID")
    private Long id;

    private String name;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private BeerType beerType;

    private int bitter;
    private double alcohol;

    @Column(length = 512)
    private String description;

    private int images;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIKEBEER_ID")
    private LikeBeer like;

    @OneToMany(mappedBy = "beer", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    protected Beer(){};

    /**
     * dto를 바탕으로 새 beer 인스턴스 값을 set
     */
    private void initBeer(BeerDto beerDto, LikeBeer likeBeer) {
        this.name = beerDto.getName();
        this.nickname = beerDto.getNickname();
        this.country = beerDto.getCountry();
        this.beerType = BeerType.getBeerTypeByValue(beerDto.getBeerType());
        this.bitter = beerDto.getBitter();
        this.alcohol = beerDto.getAlcohol();
        this.description = beerDto.getDescription();
        this.images = beerDto.getImages();
        this.like = likeBeer;
    }

    /* 생성 */
    public static Beer createBeer(BeerDto beerDto, LikeBeer likeBeer) {
        Beer beer = new Beer();
        beer.initBeer(beerDto, likeBeer);
        return beer;
    }
}
