package com.eatclub.roundtwo.challenge.mapper;

import java.time.LocalTime;

public interface TimeParserStrategy {
	LocalTime parse(String time);
}