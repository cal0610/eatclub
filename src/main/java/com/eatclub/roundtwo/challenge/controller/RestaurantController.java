package com.eatclub.roundtwo.challenge.controller;

import com.eatclub.roundtwo.challenge.client.RestaurantClient;
import com.eatclub.roundtwo.challenge.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

	private final RestaurantClient client;

	@GetMapping
	public List<RestaurantDTO> getRestaurants() {
		return client.getRestaurants().restaurants();
	}
}
