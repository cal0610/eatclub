package com.eatclub.roundtwo.challenge.service;

import com.eatclub.roundtwo.challenge.domain.Deal;
import com.eatclub.roundtwo.challenge.domain.MatchingDeal;
import com.eatclub.roundtwo.challenge.domain.Restaurant;
import com.eatclub.roundtwo.challenge.mapper.MatchingDealMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class DealService {

	private final RestaurantService restaurantService;
	private final MatchingDealMapper matchingDealMapper;

	public List<MatchingDeal> getDeals(LocalTime timeOfDay) {
		var restaurants = getRestaurants();

		return restaurants
			.stream()
			.filter(isWithinRestaurantOpeningHours(timeOfDay))
			.map(restaurant -> {
				List<Deal> filteredDeals = restaurant.deals().stream()
					.filter(isWithinDealTime(timeOfDay))
					.toList();

				return Restaurant.builder()
					.objectId(restaurant.objectId())
					.name(restaurant.name())
					.address1(restaurant.address1())
					.suburb(restaurant.suburb())
					.cuisines(restaurant.cuisines())
					.imageLink(restaurant.imageLink())
					.deals(filteredDeals)
					.build();
			})
			.flatMap(restaurantDTO -> matchingDealMapper.transform(restaurantDTO).stream())
			.toList();
	}

	private List<Restaurant> getRestaurants() {
		return restaurantService.getRestaurants();
	}

	private Predicate<Deal> isWithinDealTime(LocalTime timeOfDay) {
		return deal -> !timeOfDay.isBefore(deal.open()) && !timeOfDay.isAfter(deal.close());
	}

	private Predicate<Restaurant> isWithinRestaurantOpeningHours(LocalTime timeOfDay) {
		return restaurant -> !timeOfDay.isBefore(restaurant.open()) && !timeOfDay.isAfter(restaurant.close());
	}
}
