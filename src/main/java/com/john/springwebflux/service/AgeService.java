package com.john.springwebflux.service;

import com.john.springwebflux.model.Age;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface AgeService {
    Mono<Age> get(LocalDate birthDate);
}
