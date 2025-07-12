package com.eatclub.roundtwo.challenge.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RestaurantDTO(String objectId,
							String name,
							String address1,
							String suburb,
							List<String> cuisines,
							String imageLink,
							String open,
							String close,
							List<DealDTO> deals) {
}
