package com.todaysdrink.todaysdrink.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchDto {
	private String question;
	private String answer;
}
