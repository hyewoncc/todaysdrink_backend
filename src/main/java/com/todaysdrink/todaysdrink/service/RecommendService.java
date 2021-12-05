package com.todaysdrink.todaysdrink.service;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendService {

    private final BeerRepository beerRepository;

    // 동일 타입 기준 좋아요 순 5개 맥주 추천
    public List<Beer> getRecommendByType(Beer beer) {
        List<Beer> result = beerRepository.findTop5ByBeerTypeOrderByLike(beer);
        return result;
    }
}
