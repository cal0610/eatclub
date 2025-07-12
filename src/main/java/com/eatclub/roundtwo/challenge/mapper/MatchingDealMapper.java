package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.MatchingDeal;
import com.eatclub.roundtwo.challenge.domain.Restaurant;

import java.util.List;

public interface MatchingDealMapper {
	List<MatchingDeal> transform(Restaurant restaurant);
}
