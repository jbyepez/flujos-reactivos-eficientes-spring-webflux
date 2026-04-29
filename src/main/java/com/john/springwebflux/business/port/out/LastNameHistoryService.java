package com.john.springwebflux.business.port.out;

import com.john.springwebflux.service.dto.LastnameHistory;
import reactor.core.publisher.Mono;

public interface LastNameHistoryService {

    Mono<LastnameHistory> get(String lastName);
}
