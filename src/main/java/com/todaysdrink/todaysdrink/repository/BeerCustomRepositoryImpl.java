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
	public List<Beer> findAllByAlcoholOverPercent(Long percent) {
		List<Beer> beers = jpaQueryFactory.selectFrom(beer)
				.where(beer.alcohol.goe(percent))
				.fetch();
		return beers;
	}

	@Override
	public List<Beer> findAllByAlcoholUnderPercent(Long percent) {
		List<Beer> beers = jpaQueryFactory.selectFrom(beer)
				.where(beer.alcohol.lt(percent))
				.fetch();
		return beers;
	}

	@Override
	public List<Beer> findAllByAlcoholBetweenPercent(Long from, Long to) {
		List<Beer> beers = jpaQueryFactory.selectFrom(beer)
				.where(beer.alcohol.between(from, to))
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
	public List<Beer> findByBeerType(BeerType beerType) {
		List<Beer> beers = jpaQueryFactory.selectFrom(beer)
				.where(beer.beerType.eq(beerType))
				.fetch();
		return beers;
	}

	@Override
	public List<Beer> findByBeerTypePage(BeerType beerType, Pageable pageable) {
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

	@Override
	public List<Beer> findHardSparkling() {
		return jpaQueryFactory.selectFrom(beer)
				.where(beer.beerType.eq(BeerType.LAGER)
						.or(beer.beerType.eq(BeerType.PALE_LAGER))
						.or(beer.beerType.eq(BeerType.KOLSCH))
						.or(beer.beerType.eq(BeerType.PILSENER)))
				.fetch();
	}

	@Override
	public List<Beer> findMildSparkling() {
		return jpaQueryFactory.selectFrom(beer)
				.where(beer.beerType.eq(BeerType.ALE)
						.or(beer.beerType.eq(BeerType.IPA))
						.or(beer.beerType.eq(BeerType.PALE_ALE))
						.or(beer.beerType.eq(BeerType.STOUT))
						.or(beer.beerType.eq(BeerType.WEIZEN)))
				.fetch();
	}
}
