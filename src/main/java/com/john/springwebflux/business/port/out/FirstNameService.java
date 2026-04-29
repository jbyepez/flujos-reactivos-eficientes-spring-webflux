package com.john.springwebflux.business.port.out;

import com.john.springwebflux.service.dto.FirstName;
import reactor.core.publisher.Mono;

public interface FirstNameService {

    Mono<FirstName> get(Integer id);
}
