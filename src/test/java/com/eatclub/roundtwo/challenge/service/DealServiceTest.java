package com.eatclub.roundtwo.challenge.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealServiceTest {

	@Nested
	class GetDeals {
		@Test
		@Disabled("Not yet implemented")
		public void getDeals_shouldExcludeDealsOutsideOfRestaurantOpenHours() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void getDeals_shouldIncludeDealsWithinRestaurantAndTimeOfDay() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void getDeals_shouldExcludeDealsOutsideOfTimeOfDay() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void getDeals_shouldIncludeDeals_whenDealOpenMatchesRestaurantOpenHoursExactly() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void getDeals_shouldIncludeDeals_whenDealCloseMatchesRestaurantCloseHoursExactly() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnEmptyList_whenNoDeals() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnEmptyList_whenNoRestaurants() {
		}
	}


	@Nested
	class GetPeakTest {
		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnNullIntervalAndZero_whenNoRestaurants() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnNullIntervalAndZero_whenNoDeals() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnCorrectInterval_whenSingleDeal() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnIntervalCoveringAllDeals_whenAllDealsOverlapCompletely() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnIntervalWithMaxOverlap_whenDealsPartiallyOverlap() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldReturnEarliestPeakInterval_whenMultipleIntervalsHaveSameMaxOverlap() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldHandleDealsWithSameOpenAndCloseTime() {
		}

		@Test
		@Disabled("Not yet implemented")
		public void shouldHandleDealsWithNonOverlappingIntervals() {
		}
	}
}