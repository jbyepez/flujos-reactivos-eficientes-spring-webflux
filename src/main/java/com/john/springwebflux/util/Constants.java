package com.john.springwebflux.util;

import java.time.Duration;

public class Constants {
    private static final long BASE_MILLIS = 1000;
    public static final Duration DELAY_1 = Duration.ofMillis(BASE_MILLIS);
    public static final Duration DELAY_2 = Duration.ofMillis(BASE_MILLIS * 2);
    public static final Duration DELAY_3 = Duration.ofMillis(BASE_MILLIS * 3);
}
