package com.john.springwebflux.service.impl;

import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

public abstract class ServiceDelays {
    @Value("${services.base-delay-millis}")
    private Integer baseMillis;

    Duration delay1(){
        return Duration.ofMillis(baseMillis);
    }

    Duration delay2(){
        return Duration.ofMillis(baseMillis * 2L);
    }

    Duration delay3(){
        return Duration.ofMillis(baseMillis * 3L);
    }
}
