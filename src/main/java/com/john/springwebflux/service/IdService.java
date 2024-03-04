package com.john.springwebflux.service;

import com.john.springwebflux.model.Id;
import reactor.core.publisher.Mono;

public interface IdService {

    Mono<Id> get(Character type, String number);
}
