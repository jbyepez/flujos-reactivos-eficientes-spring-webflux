package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Lastname;
import com.john.springwebflux.service.LastNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Constants.DELAY_1;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class LastNameServiceImpl implements LastNameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Lastname> get(Integer id) {
        logger.info("getting lastname");
        return delayedMono(getLastname(id), DELAY_1);
    }

    private Lastname getLastname(Integer id) {
        return new Lastname(requireNonNull(id), "Perez");
    }
}
