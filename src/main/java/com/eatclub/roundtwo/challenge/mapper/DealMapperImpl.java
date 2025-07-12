package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.Deal;
import com.eatclub.roundtwo.challenge.dto.DealDTO;
import com.eatclub.roundtwo.challenge.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DealMapperImpl implements DealMapper {
	private final TimeParserStrategy timeParser;

	public Deal toDomain(RestaurantDTO restaurantDto, DealDTO dealDto) {
		var open = Optional.ofNullable(dealDto.open())
			.or(() -> Optional.ofNullable(dealDto.start()))
			.orElse(restaurantDto.open());

		var close = Optional.ofNullable(dealDto.close())
			.or(() -> Optional.ofNullable(dealDto.end()))
			.orElse(restaurantDto.close());

		var dealOpen = timeParser.parse(open);
		var dealClose = timeParser.parse(close);

		return Deal.builder()
			.objectId(dealDto.objectId())
			.discount(dealDto.discount())
			.dineIn(dealDto.dineIn())
			.lightning(dealDto.lightning())
			.open(dealOpen)
			.close(dealClose)
			.qtyLeft(dealDto.qtyLeft())
			.build();
	}
}
