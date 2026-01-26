package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTime {

	// No object can be created if we declare constructor as Private
	private DateTime() {

	}

	public static String getTimeWithDaysAgo(int days) {

		return Instant.now().minus(days, ChronoUnit.DAYS).toString();

	}

}
