package com.eatclub.roundtwo.challenge.domain;

import lombok.Builder;

@Builder
public record MatchingDeal(String restaurantObjectId, String restaurantName, String restaurantAddress1,
						   String restaurantSuburb,
						   String restaurantOpen, String restaurantClose, String dealObjectId, int discount,
						   boolean dineIn,
						   boolean lightning, int qtyLeft
) {
}