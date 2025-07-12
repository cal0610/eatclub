package com.eatclub.roundtwo.challenge.dto;

public record RestaurantDealDTO(
	String objectId,
	int discount,
	boolean dineIn,
	boolean lightning,
	String open,
	String close,
	String start,
	String end,
	int qtyLeft
) {
}
