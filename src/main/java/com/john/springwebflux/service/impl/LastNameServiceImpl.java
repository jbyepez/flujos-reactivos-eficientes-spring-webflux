package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Lastname;
import com.john.springwebflux.service.LastNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class LastNameServiceImpl extends ServiceDelays implements LastNameService {
    private static final Logger logger = LoggerFactory.getLogger(LastNameServiceImpl.class);

    @Override
    public Mono<Lastname> get(Integer id) {
        logger.info("getting lastname");
        return delayedMono(getLastname(id), delay1());
    }

    private Lastname getLastname(Integer id) {
        return new Lastname(requireNonNull(id), "Perez");
    }
}
