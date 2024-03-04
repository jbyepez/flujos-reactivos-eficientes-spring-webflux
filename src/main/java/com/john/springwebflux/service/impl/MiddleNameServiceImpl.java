package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.MiddleName;
import com.john.springwebflux.service.MiddleNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Constants.DELAY_3;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class MiddleNameServiceImpl implements MiddleNameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<MiddleName> get(Integer id) {
        logger.info("getting second name");
        return delayedMono(getMiddleName(id), DELAY_3);
    }

    private MiddleName getMiddleName(Integer id){
        return new MiddleName(requireNonNull(id), "Sutanito");
    }
}
