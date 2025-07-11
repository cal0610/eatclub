package com.eatclub.roundtwo.challenge.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import java.time.format.DateTimeParseException;
import java.time.zone.ZoneRulesException;

@RestControllerAdvice
public class GlobalExceptionHandler implements ProblemHandling {

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Problem> handle(DateTimeParseException ex) {
		var problem = Problem.builder()
			.withTitle("Invalid date time")
			.withStatus(Status.BAD_REQUEST)
			.withDetail("Could not parse date time: " + ex.getMessage())
			.build();
		return ResponseEntity.status(400).body(problem);
	}

	@ExceptionHandler(ZoneRulesException.class)
	public ResponseEntity<Problem> handle(ZoneRulesException ex) {
		var problem = Problem.builder()
			.withTitle("Invalid timezone")
			.withStatus(Status.BAD_REQUEST)
			.withDetail("Unknown time-zone ID: " + ex.getMessage())
			.build();
		return ResponseEntity.status(400).body(problem);
	}
}
