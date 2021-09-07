package com.todaysdrink.todaysdrink.repository;

import com.todaysdrink.todaysdrink.domain.LikeBeer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeBeerRepository extends JpaRepository<LikeBeer, Long> {
}
