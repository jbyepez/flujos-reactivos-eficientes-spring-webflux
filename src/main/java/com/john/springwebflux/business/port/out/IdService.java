package com.john.springwebflux.business.port.out;

import com.john.springwebflux.service.dto.Id;
import reactor.core.publisher.Mono;

public interface IdService {

    Mono<Id> get(Character type, String number);
}
