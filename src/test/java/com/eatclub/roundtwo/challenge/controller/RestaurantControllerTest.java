package com.eatclub.roundtwo.challenge.controller;

import com.eatclub.roundtwo.challenge.client.RestaurantClient;
import com.eatclub.roundtwo.challenge.dto.RestaurantResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RestaurantController.class)
class RestaurantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper mapper;

	@MockitoBean
	private RestaurantClient client;

	@BeforeEach
	void setup() {
		 this.mockMvc = MockMvcBuilders
			 .webAppContextSetup(wac)
			 .alwaysDo(print())
			 .build();
	}

	@Test
	void getRestaurants_shouldReturnRestaurants() throws Exception {
		// given
		var restaurantsFile = new ClassPathResource("dto/restaurant/restaurants.json").getFile();
		var restaurantResponse = mapper.readValue(restaurantsFile, RestaurantResponse.class);
		when(client.getRestaurants()).thenReturn(restaurantResponse);

		// when then
		this.mockMvc.perform(get("/restaurants"))
			.andExpect(status().is(200));
	}

}