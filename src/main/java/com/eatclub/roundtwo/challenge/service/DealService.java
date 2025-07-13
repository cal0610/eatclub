package com.eatclub.roundtwo.challenge.service;

import com.eatclub.roundtwo.challenge.domain.*;
import com.eatclub.roundtwo.challenge.mapper.MatchingDealMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

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

	/**
	 * Algorithm: For each deal's open and close time, create a DealEvent. Then sort the events. Event with the same time should prioritise sorting the type CLOSE so we count the active deals correctly.
	 * <p>
	 * Example: Given the below two events, EVENT 2 must come first after sorting.
	 * EVENT 1 - 3:00PM, OPEN
	 * EVENT 2 - 3:00PM, CLOSE
	 * <p>
	 * Runtime: O(n log n)
	 *
	 * @return the peak start, end time when there are the most simultaneous active deals.
	 */
	public PeakIntervalResult getPeakInterval() {
		var restaurants = getRestaurants();

		List<Deal> deals = restaurants.stream()
			.flatMap(i -> i.deals().stream())
			.toList();

		List<DealEvent> dealEvents = deals.stream()
			.flatMap(deal -> Stream.of(
				new DealEvent(deal.open(), DealEventType.OPEN),
				new DealEvent(deal.close(), DealEventType.CLOSE)
			))
			.sorted()
			.toList();

		int active = 0, maxActive = 0;
		LocalTime peakStart = null, peakEnd = null;

		for (DealEvent event : dealEvents) {
			if (event.type() == DealEventType.OPEN) {
				active++;
				if (active > maxActive) {
					maxActive = active;
					peakStart = event.time();
					peakEnd = null;
				}
			} else {
				if (active == maxActive && peakEnd == null) {
					peakEnd = event.time();
				}
				active--;
			}
		}

		return new PeakIntervalResult(peakStart, peakEnd, maxActive);
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
