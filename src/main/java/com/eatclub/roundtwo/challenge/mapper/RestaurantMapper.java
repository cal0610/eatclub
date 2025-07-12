package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.Restaurant;
import com.eatclub.roundtwo.challenge.dto.RestaurantDTO;

public interface RestaurantMapper {
	Restaurant toDomain(RestaurantDTO dto);
}
