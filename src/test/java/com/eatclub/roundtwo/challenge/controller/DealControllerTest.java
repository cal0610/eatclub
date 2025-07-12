package com.eatclub.roundtwo.challenge.controller;

import com.eatclub.roundtwo.challenge.domain.MatchingDeal;
import com.eatclub.roundtwo.challenge.service.DealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DealController.class)
class DealControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private DealService dealService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getDeals_shouldReturnDeals() throws Exception {
		// given
		List<MatchingDeal> deals = List.of(
			MatchingDeal.builder().dealObjectId("deal1").discount(15).build()
		);

		when(dealService.getDeals(any(LocalTime.class))).thenReturn(deals);

		// when then
		mockMvc.perform(get("/deals")
				.param("timeOfDay", "21:00"))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(deals)));
	}

}