package com.eatclub.roundtwo.challenge.client;

import com.eatclub.roundtwo.challenge.dto.RestaurantResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantClientTest {

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private RestClient restClient;

	@InjectMocks
	private RestaurantClient restaurantClient;

	@Test
	void getRestaurants_shouldReturnResponse() {
		// given
		var mockResponse = new RestaurantResponse(Collections.emptyList());
		when(restClient.get().retrieve().body(RestaurantResponse.class)).thenReturn(mockResponse);

		// when
		RestaurantResponse result = restaurantClient.getRestaurants();

		assertEquals(mockResponse, result);
	}
}