package com.eatclub.roundtwo.challenge.domain;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record Deal(
	String objectId,
	int discount,
	boolean dineIn,
	boolean lightning,
	LocalTime open,
	LocalTime close,
	int qtyLeft
) {}
