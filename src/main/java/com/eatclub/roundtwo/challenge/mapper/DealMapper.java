package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.Deal;
import com.eatclub.roundtwo.challenge.dto.DealDTO;
import com.eatclub.roundtwo.challenge.dto.RestaurantDTO;

public interface DealMapper {
	Deal toDomain(RestaurantDTO restaurantDTO, DealDTO dealDto);
}
