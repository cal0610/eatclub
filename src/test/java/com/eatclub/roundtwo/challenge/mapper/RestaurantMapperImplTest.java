package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.dto.RestaurantDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RestaurantMapperImplTest {

	private TimeParserStrategy timeParser;
	private RestaurantMapperImpl restaurantMapperImpl;

	@BeforeEach
	void setup() {
		DealMapper dealMapper = mock(DealMapper.class);
		timeParser = new HmmaTimeParser();
		restaurantMapperImpl = new RestaurantMapperImpl(timeParser, dealMapper);
	}

	@Test
	void shouldMapToDomain() {
		// given
		var dto = createRestaurantDto();

		// when
		var restaurant = restaurantMapperImpl.toDomain(dto);

		// then
		assertEquals(restaurant.objectId(), dto.objectId());
		assertEquals(restaurant.name(), dto.name());
		assertEquals(restaurant.address1(), dto.address1());
		assertEquals(restaurant.suburb(), dto.suburb());
		assertEquals(restaurant.cuisines(), dto.cuisines());
		assertEquals(restaurant.imageLink(), dto.imageLink());
		assertEquals(restaurant.deals().size(), dto.deals().size());
		assertEquals(restaurant.open(), timeParser.parse(dto.open()));
		assertEquals(restaurant.close(), timeParser.parse(dto.close()));
	}

	private RestaurantDTO createRestaurantDto() {
		return RestaurantDTO.builder()
			.objectId("DEA567C5-F64C-3C03-FF00-E3B24909BE00")
			.name("Masala Kitchen")
			.address1("55 Walsh Street")
			.suburb("Lower East")
			.cuisines(List.of("Indian", "Brazilian", "Breakfast"))
			.imageLink("https://dinnerdeal.backendless.com/api/e14e5098-2393-6d4a-ff80-f5564e042100/v1/files/restaurant_images/DEA567C5-F64C-3C03-FF00-E3B24909BE00_image_0_1520389372647.jpg")
			.open("3:00pm")
			.close("9:00pm")
			.deals(List.of())
			.build();
	}
}