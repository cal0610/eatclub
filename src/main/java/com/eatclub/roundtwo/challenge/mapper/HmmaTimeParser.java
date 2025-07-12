package com.eatclub.roundtwo.challenge.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Component
public class HmmaTimeParser implements TimeParserStrategy {
	private static final DateTimeFormatter localTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("h:mma").toFormatter();

	public LocalTime toLocalTime(String time) {
		return LocalTime.parse(time, localTimeFormatter);
	}

	@Override
	public LocalTime parse(String time) {
		return LocalTime.parse(time, localTimeFormatter);
	}
}


