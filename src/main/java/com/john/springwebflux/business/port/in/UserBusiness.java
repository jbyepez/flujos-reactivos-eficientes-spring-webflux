package com.john.springwebflux.business.port.in;

import com.john.springwebflux.domain.User;
import reactor.core.publisher.Mono;

public interface UserBusiness {
    Mono<User> getUser(Character docType, String docNumber);
}
