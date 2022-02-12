package com.todaysdrink.todaysdrink.service;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.BeerRecommends;
import com.todaysdrink.todaysdrink.domain.BeerType;
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

	private static final int ALCOHOL_INDEX = 0;
	private static final Long HIGH_ALCOHOL_RANGE = 5L;
	private static final Long LOW_ALCOHOL_RANGE = 4L;

	private static final int SPARKLING_INDEX = 1;

	private static final int BITTER_INDEX = 2;
	private static final double HIGH_BITTER_RANGE = 6.0;
	private static final double MIDDLE_BITTER_START = 3.6;
	private static final double MIDDLE_BITTER_END = 5.9;
	private static final double LOW_BITTER_RANGE = 3.5;

	public void getMatchBeers(List<MatchDto> matchDtos) {
		BeerRecommends beerRecommends = new BeerRecommends(new HashMap<>());
		beerRecommends.addListInRecommend(matchByAlcohol(matchDtos.get(ALCOHOL_INDEX)));
		beerRecommends.addListInRecommend(matchBySparkling(matchDtos.get(SPARKLING_INDEX)));
		beerRecommends.addListInRecommend(matchByBitter(matchDtos.get(BITTER_INDEX)));
		System.out.println(beerRecommends.toString());
		List<Beer> result = beerRecommends.pickTop3();
	}

	private List<Beer> matchByAlcohol(MatchDto matchDto) {
		String answer = matchDto.getAnswer();

		if (answer.equals("HIGH")) {
			return beerRepository.findAllByAlcoholOverPercent(HIGH_ALCOHOL_RANGE);
		}

		if (answer.equals("MIDDLE")) {
			return beerRepository.findAllByAlcoholBetweenPercent(LOW_ALCOHOL_RANGE, HIGH_ALCOHOL_RANGE);
		}

		if (answer.equals("LOW")) {
			return beerRepository.findAllByAlcoholUnderPercent(LOW_ALCOHOL_RANGE);
		}

		if (answer.equals("ZERO")) {
			return beerRepository.findByBeerType(BeerType.NON_ALCOHOL);
		}

		return new ArrayList<>();
	}

	private List<Beer> matchBySparkling(MatchDto matchDto) {
		String answer = matchDto.getAnswer();

		if (answer.equals("HARD")) {
			return beerRepository.findHardSparkling();
		}

		if (answer.equals("MILD")) {
			return beerRepository.findMildSparkling();
		}

		return new ArrayList<>();
	}

	private List<Beer> matchByBitter(MatchDto matchDto) {
		String answer = matchDto.getAnswer();

		if (answer.equals("HIGH")) {
			return beerRepository.findBitterOverPoint(HIGH_BITTER_RANGE);
		}

		if (answer.equals("MIDDLE")) {
			return beerRepository.findBitterBetweenPoints(MIDDLE_BITTER_START, MIDDLE_BITTER_END);
		}

		if (answer.equals("LOW")) {
			return beerRepository.findBitterUnderPointNotZero(LOW_BITTER_RANGE);
		}

		if (answer.equals("ZERO")) {
			return beerRepository.findBitterZero();
		}

		return new ArrayList<>();
	}
}
