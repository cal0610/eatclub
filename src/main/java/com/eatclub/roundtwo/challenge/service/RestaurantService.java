package com.eatclub.roundtwo.challenge.service;

import com.eatclub.roundtwo.challenge.client.RestaurantClient;
import com.eatclub.roundtwo.challenge.domain.Restaurant;
import com.eatclub.roundtwo.challenge.mapper.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
	private final RestaurantClient client;
	private final RestaurantMapper mapper;

	public RestaurantService(RestaurantClient client, RestaurantMapper mapper) {
		this.client = client;
		this.mapper = mapper;
	}

	public List<Restaurant> getRestaurants() {
		return client.getRestaurants().restaurants()
			.stream()
			.map(mapper::toDomain)
			.toList();
	}
}
