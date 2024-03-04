package com.john.springwebflux.service;

import com.john.springwebflux.model.Numerology;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface NumerologyService {

    Mono<Numerology> get(String firstName, String secondName, String lastName, LocalDate birthDate);
}
