package com.john.springwebflux.service;

import com.john.springwebflux.model.Birthdate;
import reactor.core.publisher.Mono;

public interface BirthdateService {

    Mono<Birthdate> get(Integer id);
}
