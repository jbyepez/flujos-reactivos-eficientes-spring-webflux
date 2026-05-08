package com.john.springwebflux.business.port.out;

import com.john.springwebflux.business.port.out.dto.Id;
import reactor.core.publisher.Mono;

public interface IdService {

    Mono<Id> get(Character type, String number);
}
