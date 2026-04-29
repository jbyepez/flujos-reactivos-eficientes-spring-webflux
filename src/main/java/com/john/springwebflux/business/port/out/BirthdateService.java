package com.john.springwebflux.business.port.out;

import com.john.springwebflux.service.dto.Birthdate;
import reactor.core.publisher.Mono;

public interface BirthdateService {

    Mono<Birthdate> get(Integer id);
}
