package com.john.springwebflux.service;

import com.john.springwebflux.model.Lastname;
import reactor.core.publisher.Mono;

public interface LastNameService {

    Mono<Lastname> get(Integer id);
}
