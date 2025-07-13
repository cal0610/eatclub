package com.eatclub.roundtwo.challenge.domain;

import java.time.LocalTime;

public record DealEvent(LocalTime time, DealEventType type) implements Comparable<DealEvent> {

	@Override
	public int compareTo(DealEvent o) {
		int cmp = this.time.compareTo(o.time);
		if (cmp != 0) return cmp;
		if (this.type == o.type) return 0;
		return this.type == DealEventType.CLOSE ? -1 : 1; // if time is equal, we must prioritise sorting the event that is type CLOSE to ensure we count the active deals correctly
	}
}
