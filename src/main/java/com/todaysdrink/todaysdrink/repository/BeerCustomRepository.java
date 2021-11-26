package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeerCustomRepository {
    Page<Beer> findAllOrderByLike(Pageable pageable);
    List<Beer> findTop5OrderByLike();
}
