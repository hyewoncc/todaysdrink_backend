package com.todaysdrink.todaysdrink.api.controller;

import com.todaysdrink.todaysdrink.dto.MatchDto;
import com.todaysdrink.todaysdrink.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match")
public class MatchController {

	private final MatchService matchService;

	// 취향 맥주 추천
	@PostMapping("")
	public void findMatchBeers(@RequestBody List<MatchDto> matchDtos) {
		matchService.getMatchBeers(matchDtos);
	}
}
