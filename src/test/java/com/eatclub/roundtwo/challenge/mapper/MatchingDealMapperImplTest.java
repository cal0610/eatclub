package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.Deal;
import com.eatclub.roundtwo.challenge.domain.MatchingDeal;
import com.eatclub.roundtwo.challenge.domain.Restaurant;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchingDealMapperImplTest {

	private final MatchingDealMapperImpl mapper = new MatchingDealMapperImpl();

	@Test
	void shouldMapToMatchingDeals() {
		// given a restaurant with one deal
		var restaurantId = "rest-456";
		var restaurantName = "Restaurant";
		var restaurantAddress = "123 Main St";
		var restaurantSuburb = "Central";
		var dealId = "deal-123";
		var discount = 20;
		var dineIn = true;
		var lightning = false;
		var qtyLeft = 5;
		var dealOpen = LocalTime.of(9, 0);
		var dealClose = LocalTime.of(17, 0);

		var deal = Deal.builder()
			.objectId(dealId)
			.discount(discount)
			.dineIn(dineIn)
			.lightning(lightning)
			.open(dealOpen)
			.close(dealClose)
			.qtyLeft(qtyLeft)
			.build();

		var restaurant = Restaurant.builder()
			.objectId(restaurantId)
			.name(restaurantName)
			.address1(restaurantAddress)
			.suburb(restaurantSuburb)
			.cuisines(List.of("Thai", "Indian"))
			.imageLink("http://example.com/image.jpg")
			.open(LocalTime.of(8, 0))
			.close(LocalTime.of(22, 0))
			.deals(List.of(deal))
			.build();

		// when
		List<MatchingDeal> result = mapper.transform(restaurant);

		// then
		assertThat(result).hasSize(1);
		MatchingDeal matchingDeal = result.getFirst();

		assertRestaurantProperties(restaurant, matchingDeal);
		assertEquals(deal.open().toString(), matchingDeal.restaurantOpen());
		assertEquals(deal.close().toString(), matchingDeal.restaurantClose());
		assertEquals(dealId, matchingDeal.dealObjectId());
		assertEquals(discount, matchingDeal.discount());
		assertEquals(dineIn, matchingDeal.dineIn());
		assertEquals(lightning, matchingDeal.lightning());
		assertEquals(qtyLeft, matchingDeal.qtyLeft());
	}

	private void assertRestaurantProperties(Restaurant restaurant, MatchingDeal matchingDeal) {
		assertThat(matchingDeal.restaurantObjectId()).isEqualTo(restaurant.objectId());
		assertThat(matchingDeal.restaurantName()).isEqualTo(restaurant.name());
		assertThat(matchingDeal.restaurantAddress1()).isEqualTo(restaurant.address1());
		assertThat(matchingDeal.restaurantSuburb()).isEqualTo(restaurant.suburb());

	}
}