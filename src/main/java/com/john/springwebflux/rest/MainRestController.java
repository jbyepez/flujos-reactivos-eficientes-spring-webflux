package com.john.springwebflux.rest;

import com.john.springwebflux.business.UserBusiness;
import com.john.springwebflux.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MainRestController {
    private static final Logger logger = LoggerFactory.getLogger(MainRestController.class);
    private final UserBusiness userBusiness;

    public MainRestController(@Qualifier("UserBusiness3") UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @GetMapping("user")
    Mono<User> getUser(
            @RequestParam("doc-type") Character docType,
            @RequestParam("doc-number") String docNumber
    ) {
        return userBusiness.getUser(docType, docNumber)
                .doOnSuccess(user -> logger.info("returning response"));
    }
}
