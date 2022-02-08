package com.todaysdrink.todaysdrink.service;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerRecommends;
import com.todaysdrink.todaysdrink.domain.BeerType;
import com.todaysdrink.todaysdrink.domain.match.Alcohol;
import com.todaysdrink.todaysdrink.domain.match.Sparkling;
import com.todaysdrink.todaysdrink.dto.MatchDto;
import com.todaysdrink.todaysdrink.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchService {

	private final BeerRepository beerRepository;

	private final int ALCOHOL_INDEX = 0;
	private final Long HIGH_ALCOHOL_RANGE = 5L;
	private final Long LOW_ALCOHOL_RANGE = 4L;

	private final int SPARKLING_INDEX = 1;

	public void getMatchBeers(List<MatchDto> matchDtos) {
		BeerRecommends beerRecommends = new BeerRecommends(new HashMap<>());
		beerRecommends.addListInRecommend(matchByAlcohol(matchDtos.get(ALCOHOL_INDEX)));
		beerRecommends.addListInRecommend(matchBySparkling(matchDtos.get(SPARKLING_INDEX)));
		List<Beer> result = beerRecommends.pickTop3();
	}

	public List<Beer> matchByAlcohol(MatchDto matchDto) {
		String answer = matchDto.getAnswer();

		if (answer.equals(Alcohol.HIGH.name())) {
			return beerRepository.findAllByAlcoholOverPercent(HIGH_ALCOHOL_RANGE);
		}

		if (answer.equals(Alcohol.MIDDLE.name())) {
			return beerRepository.findAllByAlcoholBetweenPercent(LOW_ALCOHOL_RANGE, HIGH_ALCOHOL_RANGE);
		}

		if (answer.equals(Alcohol.LOW.name())) {
			return beerRepository.findAllByAlcoholUnderPercent(LOW_ALCOHOL_RANGE);
		}

		if (answer.equals(Alcohol.ZERO.name())) {
			return beerRepository.findByBeerType(BeerType.NON_ALCOHOL);
		}

		return new ArrayList<>();
	}

	public List<Beer> matchBySparkling(MatchDto matchDto) {
		String answer = matchDto.getAnswer();

		if (answer.equals(Sparkling.HARD.name())) {
			return beerRepository.findHardSparkling();
		}

		if (answer.equals(Sparkling.MILD.name())) {
			return beerRepository.findMildSparkling();
		}

		return new ArrayList<>();
	}
}
