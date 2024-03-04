package com.john.springwebflux.service;

import com.john.springwebflux.model.LastnameHistory;
import reactor.core.publisher.Mono;

public interface LastNameHistoryService {

    Mono<LastnameHistory> get(String lastName);
}
