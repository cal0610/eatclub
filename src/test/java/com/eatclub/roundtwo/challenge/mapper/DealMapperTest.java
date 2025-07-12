package com.eatclub.roundtwo.challenge.mapper;

import com.eatclub.roundtwo.challenge.domain.Deal;
import com.eatclub.roundtwo.challenge.dto.DealDTO;
import com.eatclub.roundtwo.challenge.dto.RestaurantDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealMapperTest {

	private DealMapperImpl dealMapperImpl;
	private ObjectMapper objectMapper;
	private HmmaTimeParser hmmaTimeParser;

	@BeforeEach
	void setup() {
		hmmaTimeParser = new HmmaTimeParser();
		dealMapperImpl = new DealMapperImpl(hmmaTimeParser);
		objectMapper = new ObjectMapper();
	}

	@Test
	void shouldMapToDomain_whenDealHasOpenAndClose() throws Exception {
		// given
		var dealsFile = new ClassPathResource("dto/deal/deal-with-open-and-close.json").getFile();
		var dto = objectMapper.readValue(dealsFile, DealDTO.class);

		// when
		var restaurantDto = createRestaurantDto("10:00am", "3:00pm");
		var deal = dealMapperImpl.toDomain(restaurantDto, dto);

		// then
		assertBasicProperties(deal, dto);
		assertTimeEquals(deal.open(), dto.open());
		assertTimeEquals(deal.close(), dto.close());
	}

	@Test
	void shouldFallBackToStartAndEnd_whenOpenAndCloseIsNull() throws Exception {
		// given
		var dealsFile = new ClassPathResource("dto/deal/deal-with-start-and-end.json").getFile();
		var dto = objectMapper.readValue(dealsFile, DealDTO.class);

		// when
		var restaurantDto = createRestaurantDto("10:00am", "3:00pm");
		var deal = dealMapperImpl.toDomain(restaurantDto, dto);

		// then
		assertBasicProperties(deal, dto);
		assertTimeEquals(deal.open(), dto.start());
		assertTimeEquals(deal.close(), dto.end());
	}


	@Test
	void shouldFallBackToRestaurantStartAndEnd_whenOpenCloseStartEndIsNull() throws Exception {
		// given
		var dealsFile = new ClassPathResource("dto/deal/deal-with-only-restaurant-open-and-end.json").getFile();
		var dto = objectMapper.readValue(dealsFile, DealDTO.class);

		// when
		var restaurantOpen = "1:00am";
		var restaurantClose = "2:00am";
		var restaurantDto = createRestaurantDto(restaurantOpen, restaurantClose);
		var deal = dealMapperImpl.toDomain(restaurantDto, dto);


		// then
		assertBasicProperties(deal, dto);
		assertTimeEquals(deal.open(), restaurantOpen);
		assertTimeEquals(deal.close(), restaurantClose);
	}

	private RestaurantDTO createRestaurantDto(String open, String close) {
		return RestaurantDTO.builder().open(open).close(close).build();
	}

	private void assertBasicProperties(Deal deal, DealDTO dto) {
		assertEquals(deal.objectId(), dto.objectId());
		assertEquals(deal.discount(), dto.discount());
		assertEquals(deal.dineIn(), dto.dineIn());
		assertEquals(deal.lightning(), dto.lightning());
	}

	private void assertTimeEquals(LocalTime localTime, String time) {
		assertEquals(localTime, hmmaTimeParser.toLocalTime(time));
	}
}