package com.eatclub.roundtwo.challenge.dto;

public record DealDTO(
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
