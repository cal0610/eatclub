package com.eatclub.roundtwo.challenge.client;

import com.eatclub.roundtwo.challenge.dto.RestaurantResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class RestaurantClient {
	private final RestClient client;

	public RestaurantClient(RestClient client) {
		this.client = client;
	}

	@Retryable(retryFor = HttpServerErrorException.class)
	public RestaurantResponse getRestaurants() {
		log.atDebug().log("Calling restaurant API to fetch restaurants");

		return client.get()
			.retrieve()
			.body(RestaurantResponse.class);
	}
}
