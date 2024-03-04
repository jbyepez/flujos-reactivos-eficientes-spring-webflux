package com.john.springwebflux.service;

import com.john.springwebflux.model.MiddleName;
import reactor.core.publisher.Mono;

public interface MiddleNameService {

    Mono<MiddleName> get(Integer id);
}
