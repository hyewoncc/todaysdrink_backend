package com.todaysdrink.todaysdrink.service;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerType;
import com.todaysdrink.todaysdrink.domain.Country;
import com.todaysdrink.todaysdrink.domain.LikeBeer;
import com.todaysdrink.todaysdrink.dto.BeerDto;
import com.todaysdrink.todaysdrink.repository.BeerRepository;
import com.todaysdrink.todaysdrink.repository.LikeBeerRepository;
import com.todaysdrink.todaysdrink.util.FilterParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.todaysdrink.todaysdrink.util.FilterParser.KEY_INDEX;
import static com.todaysdrink.todaysdrink.util.FilterParser.VALUE_INDEX;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final LikeBeerRepository likeBeerRepository;
    private FilterParser filterProcessor = new FilterParser();

    // 맥주 추가
    @Transactional
    public Beer saveBeer(BeerDto beerDto) {
        LikeBeer likeBeer = this.addLikeBeer();
        Beer beer = Beer.createBeer(beerDto, likeBeer);
        beerRepository.save(beer);
        return beer;
    }

    // 맥주 좋아요 추가
    @Transactional
    public LikeBeer addLikeBeer() {
        LikeBeer likeBeer = LikeBeer.createLikeBeer();
        likeBeerRepository.save(likeBeer);
        return likeBeer;
    }

    // 단일 맥주 반환
    public Optional<Beer> getOneBeer(Long beerId) {
        Optional<Beer> beer = beerRepository.findById(beerId);
        return beer;
    }

    // 맥주 목록 반환
    public List<Beer> getBeerList(Pageable pageable) {
        List<Beer> beers = beerRepository.findAllOrderByLike(pageable);
        return beers;
    }

    // 필터가 적용 된 맥주 목록 반환
    public List<Beer> getBeerListByFilter(List<String> filter, Pageable pageable) {
        List<Beer> beers = new ArrayList<>();
        String option  = filter.get(KEY_INDEX);
        String value = filter.get(VALUE_INDEX);
        if (option.equals("country")) {
            beers = beerRepository.findByCountry(Country.getCountryByValue(value), pageable);
        } else if (option.equals("type")) {
            beers = beerRepository.findByBeerType(BeerType.getBeerTypeByValue(value), pageable);
        } else if (option.equals("alcohol")) {
            if (value.equals("desc")) {
                beers = beerRepository.findAllOrderByAlcohol(pageable);
            } else if (value.equals("asc")) {
                beers = beerRepository.findAllOrderByAlcoholAsc(pageable);
            }
        } else {
            beers = beerRepository.findAllOrderByLike(pageable);
        }

        return beers;
    }

    // 맥주 좋아요 조회
    public Optional<LikeBeer> getOneLikeBeer(Long likeBeerId) {
        Optional<LikeBeer> likeBeer = likeBeerRepository.findById(likeBeerId);
        return likeBeer;
    }

    // 맥주 좋아요 실행 후 조회
    @Transactional
    public LikeBeer upLikeBeer(Long likeBeerId) {
        LikeBeer likebeer = likeBeerRepository.getById(likeBeerId);
        likebeer.upCount();
        likeBeerRepository.save(likebeer);
        return likebeer;
    }

    // 맥주 좋아요 취소 후 조회
    @Transactional
    public LikeBeer downLikeBeer(Long likeBeerId) {
        LikeBeer likebeer = likeBeerRepository.getById(likeBeerId);
        likebeer.downCount();
        likeBeerRepository.save(likebeer);
        return likebeer;
    }

    // 좋아요 탑5 맥주 조회
    public List<Beer> getTop5BeerByLike() {
        List<Beer> beers = beerRepository.findTop5OrderByLike();
        return beers;
    }
}
