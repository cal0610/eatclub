package com.eatclub.roundtwo.challenge.controller;

import com.eatclub.roundtwo.challenge.domain.MatchingDeal;
import com.eatclub.roundtwo.challenge.service.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DealController {

	private final DealService dealService;

	@GetMapping("/deals")
	public List<MatchingDeal> getDeals(@RequestParam(name = "timeOfDay") LocalTime localTime, @RequestParam(name = "timezone", required = false) String timezone) {
		log.atDebug().log("Getting deals for time of day: {}", localTime);

		ZoneId tz = (timezone != null && !timezone.isBlank())
			? ZoneId.of(timezone)
			: ZoneId.systemDefault(); // I think it makes sense to have a timezone, you may have deals for different countries!

		return dealService.getDeals(localTime);
	}
}

