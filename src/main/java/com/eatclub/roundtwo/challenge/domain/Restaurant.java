package com.eatclub.roundtwo.challenge.domain;

import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
public record Restaurant(
	String objectId,
	String name,
	String address1,
	String suburb,
	List<String> cuisines,
	String imageLink,
	LocalTime open,
	LocalTime close,
	List<Deal> deals
) {}
