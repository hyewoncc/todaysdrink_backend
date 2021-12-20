package com.todaysdrink.todaysdrink.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import com.todaysdrink.todaysdrink.domain.Country;
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
                .limit(pageable.getPageSize())
                .fetch();
        return beers;
    }

    @Override
    public List<Beer> findAllOrderByLikeAsc(Pageable pageable) {
        List<Beer> beers = jpaQueryFactory.selectFrom(beer)
                .orderBy(beer.like.count.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return beers;
    }

    @Override
    public List<Beer> findAllOrderByAlcohol(Pageable pageable) {
        List<Beer> beers = jpaQueryFactory.selectFrom(beer)
                .orderBy(beer.alcohol.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return beers;
    }

    @Override
    public List<Beer> findAllOrderByAlcoholAsc(Pageable pageable) {
        List<Beer> beers = jpaQueryFactory.selectFrom(beer)
                .orderBy(beer.alcohol.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return beers;
    }

    @Override
    public List<Beer> findByCountry(Country country, Pageable pageable) {
        List<Beer> beers = jpaQueryFactory.selectFrom(beer)
                .where(beer.country.eq(country))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return beers;
    }

    @Override
    public List<Beer> findByBeerType(BeerType beerType, Pageable pageable) {
        List<Beer> beers = jpaQueryFactory.selectFrom(beer)
                .where(beer.beerType.eq(beerType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
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
    public List<Beer> findTop5ByBeerTypeOrderByLike(Beer baseBeer) {
        return jpaQueryFactory.selectFrom(beer)
                .where(beer.beerType.eq(baseBeer.getBeerType()))
                .where(beer.id.ne(baseBeer.getId()))
                .orderBy(beer.like.count.desc())
                .limit(5)
                .fetch();
    }
}
