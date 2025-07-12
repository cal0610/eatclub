package com.eatclub.roundtwo.challenge.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HmmaTimeParserTest {

	private HmmaTimeParser utils;

	@BeforeEach
	void setup() {
		utils = new HmmaTimeParser();
	}

	@ParameterizedTest
	@MethodSource("input")
	public void shouldParseLocalTime(String input, LocalTime expected) {
		assertEquals(expected, (utils.toLocalTime(input)));
	}

	@Test
	public void invalidTime_shouldThrowException() {
		assertThrows(DateTimeParseException.class, () -> utils.toLocalTime("21:00am"));
	}

	private static Stream<Arguments> input() {
		return Stream.of(
			Arguments.of("10:30PM", LocalTime.of(22, 30)),
			Arguments.of("10:30pm", LocalTime.of(22, 30)),
			Arguments.of("1:00am", LocalTime.of(1, 0))
		);
	}


}