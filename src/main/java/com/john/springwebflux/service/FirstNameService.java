package com.john.springwebflux.service;

import com.john.springwebflux.model.FirstName;
import reactor.core.publisher.Mono;

public interface FirstNameService {

    Mono<FirstName> get(Integer id);
}
