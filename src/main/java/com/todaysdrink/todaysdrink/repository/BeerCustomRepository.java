package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeerCustomRepository {
    List<Beer> findAllOrderByLike(Pageable pageable);
    List<Beer> findTop5OrderByLike();
    List<Beer> findTop5ByBeerTypeOrderByLike(Beer beer);
}
