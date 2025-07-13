package com.eatclub.roundtwo.challenge.domain;

import java.time.LocalTime;

public record PeakIntervalResult(LocalTime start, LocalTime end, int maxDeals) {

}
