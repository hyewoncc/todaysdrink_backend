package com.todaysdrink.todaysdrink.service;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.LikeBeer;
import com.todaysdrink.todaysdrink.dto.BeerDto;
import com.todaysdrink.todaysdrink.repository.BeerRepository;
import com.todaysdrink.todaysdrink.repository.LikeBeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final LikeBeerRepository likeBeerRepository;


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
    public Page<Beer> getBeerList(Pageable pageable) {
        Page<Beer> listBeers = beerRepository.findAll(pageable);
        return listBeers;
    }


    // 맥주 좋아요 조회
    public Optional<LikeBeer> getOneLikeBeer(Long likeBeerId) {
        Optional<LikeBeer> likeBeer = likeBeerRepository.findById(likeBeerId);
        return likeBeer;
    }


    // 맥주 좋아요 실행 후 조회
    @Transactional
    public Long upLikeBeer(Long likeBeerId) {
        LikeBeer likebeer = likeBeerRepository.getById(likeBeerId);
        likebeer.upCount();
        likeBeerRepository.save(likebeer);
        return likebeer.getCount();
    }


    // 맥주 좋아요 취소 후 조회
    @Transactional
    public Long downLikeBeer(Long likeBeerId) {
        LikeBeer likebeer = likeBeerRepository.getById(likeBeerId);
        likebeer.downCount();
        likeBeerRepository.save(likebeer);
        return likebeer.getCount();
    }
}
