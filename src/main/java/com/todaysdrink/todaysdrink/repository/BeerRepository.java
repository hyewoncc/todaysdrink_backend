package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import com.todaysdrink.todaysdrink.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long>, BeerCustomRepository {
}
