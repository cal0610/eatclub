package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.Deal;
import com.eatclub.roundtwo.challenge.domain.MatchingDeal;
import com.eatclub.roundtwo.challenge.domain.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchingDealMapperImpl implements MatchingDealMapper {

	@Override
	public List<MatchingDeal> transform(Restaurant restaurant) {
		return restaurant.deals()
			.stream()
			.map(deal -> mapToMatchingDeal(restaurant, deal))
			.toList();
	}

	public MatchingDeal mapToMatchingDeal(Restaurant restaurant, Deal deal) {
		return MatchingDeal.builder()
			.restaurantObjectId(restaurant.objectId())
			.restaurantName(restaurant.name())
			.restaurantAddress1(restaurant.address1())
			.restaurantSuburb(restaurant.suburb())
			.restaurantOpen(deal.open().toString())
			.restaurantClose(deal.close().toString())
			.dealObjectId(deal.objectId())
			.discount(deal.discount())
			.dineIn(deal.dineIn())
			.lightning(deal.lightning())
			.qtyLeft(deal.qtyLeft())
			.build();
	}
}
