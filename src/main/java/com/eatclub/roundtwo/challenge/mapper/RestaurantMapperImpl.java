package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.Restaurant;
import com.eatclub.roundtwo.challenge.dto.RestaurantDTO;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapperImpl implements RestaurantMapper {

	private final TimeParserStrategy timeParser;
	private final DealMapper dealMapper;

	public RestaurantMapperImpl(TimeParserStrategy timeParser, DealMapper dealMapper) {
		this.timeParser = timeParser;
		this.dealMapper = dealMapper;
	}

	@Override
	public Restaurant toDomain(RestaurantDTO dto) {
		var restaurantOpen = timeParser.parse(dto.open());
		var restaurantClose = timeParser.parse(dto.close());

		return Restaurant.builder()
			.objectId(dto.objectId())
			.name(dto.name())
			.address1(dto.address1())
			.suburb(dto.suburb())
			.cuisines(dto.cuisines())
			.imageLink(dto.imageLink())
			.open(restaurantOpen)
			.close(restaurantClose)
			.deals(dto.deals().stream().map(deal -> dealMapper.toDomain(dto, deal)).toList())
			.build();
	}
}
