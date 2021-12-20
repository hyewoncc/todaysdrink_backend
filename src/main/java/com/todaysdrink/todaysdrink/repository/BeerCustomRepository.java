package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import com.todaysdrink.todaysdrink.domain.Country;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeerCustomRepository {
    List<Beer> findAllOrderByLike(Pageable pageable);
    List<Beer> findAllOrderByLikeAsc(Pageable pageable);
    List<Beer> findAllOrderByAlcohol(Pageable pageable);
    List<Beer> findAllOrderByAlcoholAsc(Pageable pageable);
    List<Beer> findByCountry(Country country, Pageable pageable);
    List<Beer> findByBeerType(BeerType beerType, Pageable pageable);
    List<Beer> findTop5OrderByLike();
    List<Beer> findTop5ByBeerTypeOrderByLike(Beer beer);
}
