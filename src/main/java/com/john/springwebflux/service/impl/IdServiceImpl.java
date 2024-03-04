package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Id;
import com.john.springwebflux.service.IdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Constants.DELAY_1;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class IdServiceImpl implements IdService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Id> get(Character type, String number) {
        logger.info("getting id");
        return delayedMono(getDocument(type, number), DELAY_1);
    }

    private Id getDocument(Character type, String number){
        return new Id(1, requireNonNull(type), requireNonNull(number));
    }
}
