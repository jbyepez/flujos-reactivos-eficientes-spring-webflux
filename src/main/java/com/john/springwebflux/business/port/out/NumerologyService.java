package com.john.springwebflux.business.port.out;

import com.john.springwebflux.service.dto.Numerology;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface NumerologyService {

    Mono<Numerology> get(String firstName, String secondName, String lastName, LocalDate birthDate);
}
