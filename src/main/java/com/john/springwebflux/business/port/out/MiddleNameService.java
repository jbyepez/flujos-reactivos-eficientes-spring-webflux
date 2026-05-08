package com.john.springwebflux.business.port.out;

import com.john.springwebflux.business.port.out.dto.MiddleName;
import reactor.core.publisher.Mono;

public interface MiddleNameService {

    Mono<MiddleName> get(Integer id);
}
