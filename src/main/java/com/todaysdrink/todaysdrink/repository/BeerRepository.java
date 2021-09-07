package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
