package com.todaysdrink.todaysdrink.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BeerRecommends {

	private Map<Beer, Integer> recommendScores;

	public BeerRecommends(Map<Beer, Integer> recommendScores) {
		this.recommendScores = recommendScores;
	}

	public void addListInRecommend(List<Beer> beers) {
		for (Beer beer : beers) {
			giveScore(beer);
		}
	}

	public void giveScore(Beer beer) {
		if (!recommendScores.containsKey(beer)) {
			recommendScores.put(beer, 1);
		} else {
			recommendScores.put(beer, recommendScores.get(beer) + 1);
		}
	}

	public List<Beer> pickTop3() {
		List<Beer> pickedBeers = new ArrayList<>();
		List<Map.Entry<Beer, Integer>> entryList = new LinkedList<>(recommendScores.entrySet());
		entryList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		for (int i = 0; i < 3; i++) {
			pickedBeers.add(entryList.get(i).getKey());
		}
		return pickedBeers;
	}

	@Override
	public String toString() {
		String text = "";
		for (Beer beer : recommendScores.keySet()) {
			text += (beer.getNickname() + " : ");
			text += (recommendScores.get(beer) + "\n");
		}
		return text;
	}
}
