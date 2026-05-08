package com.john.springwebflux.business.port.out;

import com.john.springwebflux.business.port.out.dto.Numerology;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface NumerologyService {

    Mono<Numerology> get(String firstName, String middleName, String lastName, LocalDate birthDate);
}
