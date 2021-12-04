package com.todaysdrink.todaysdrink.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.todaysdrink.todaysdrink.domain.QBeer.beer;

@Repository
@RequiredArgsConstructor
public class BeerCustomRepositoryImpl implements BeerCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Beer> findAllOrderByLike(Pageable pageable) {
        List<Beer> beers = jpaQueryFactory.selectFrom(beer)
                .orderBy(beer.like.count.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageNumber())
                .fetch();
        return beers;
    }

    @Override
    public List<Beer> findTop5OrderByLike() {
        return jpaQueryFactory.selectFrom(beer)
                .orderBy(beer.like.count.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<Beer> findTop5ByBeerTypeOrderByLike(BeerType beerType) {
        return jpaQueryFactory.selectFrom(beer)
                .where(beer.beerType.eq(beerType))
                .orderBy(beer.like.count.desc())
                .limit(5)
                .fetch();
    }
}
