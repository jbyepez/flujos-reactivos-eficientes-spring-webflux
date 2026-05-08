package com.john.springwebflux.business.port.out;

import com.john.springwebflux.business.port.out.dto.Lastname;
import reactor.core.publisher.Mono;

public interface LastNameService {

    Mono<Lastname> get(Integer id);
}
