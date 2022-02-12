package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import com.todaysdrink.todaysdrink.domain.Country;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeerCustomRepository {
    List<Beer> findAllOrderByLike(Pageable pageable);
    List<Beer> findAllOrderByLikeAsc(Pageable pageable);
    List<Beer> findTop5OrderByLike();

    List<Beer> findAllOrderByAlcohol(Pageable pageable);
    List<Beer> findAllOrderByAlcoholAsc(Pageable pageable);
    List<Beer> findAllByAlcoholOverPercent(Long percent);
    List<Beer> findAllByAlcoholUnderPercent(Long percent);
    List<Beer> findAllByAlcoholBetweenPercent(Long from, Long to);

    List<Beer> findByCountry(Country country, Pageable pageable);

    List<Beer> findByBeerType(BeerType beerType);
    List<Beer> findByBeerTypePage(BeerType beerType, Pageable pageable);
    List<Beer> findTop5ByBeerTypeOrderByLike(Beer beer);

    List<Beer> findHardSparkling();
    List<Beer> findMildSparkling();

    List<Beer> findBitterOverPoint(Double point);
    List<Beer> findBitterBetweenPoints(Double lowPoint, Double highPoint);
    List<Beer> findBitterUnderPointNotZero(Double point);
    List<Beer> findBitterZero();
}
