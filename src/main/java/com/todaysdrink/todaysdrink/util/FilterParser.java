package com.todaysdrink.todaysdrink.util;

import java.util.Arrays;
import java.util.List;

public class FilterParser {

	public static final int KEY_INDEX = 0;
	public static final int VALUE_INDEX = 1;

	public List<String> getFilterKeyAndValue(String rawJson) {
		return Arrays.asList(rawJson
				.replace("{", "")
				.replace("}", "")
				.replace("\"", "")
				.split(":"));
	}
}
