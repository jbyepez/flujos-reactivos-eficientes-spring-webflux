package com.john.springwebflux.business.port.out;

import com.john.springwebflux.business.port.out.dto.Age;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface AgeService {
    Mono<Age> get(LocalDate birthDate);
}
