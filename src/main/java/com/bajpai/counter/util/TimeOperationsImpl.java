package com.bajpai.counter.util;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeOperationsImpl implements TimeOperations{

	
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX").withZone(ZoneId.from(ZoneOffset.UTC));
	
	Date start,queried;
	
	public String getCurrentTime() {
		return Instant.now().with(ChronoField.NANO_OF_SECOND,0).toString();
	}
	
	public boolean isCounterReady(String created, int delay) {
		TemporalAccessor temporalAccessor = formatter.parse(created);
		long difference = Duration.between(Instant.from(temporalAccessor),
										   Instant.now().with(ChronoField.NANO_OF_SECOND,0)).toSeconds();
		System.out.println("Time difference:"+difference);
		return !(difference < delay);
		
	}
}
