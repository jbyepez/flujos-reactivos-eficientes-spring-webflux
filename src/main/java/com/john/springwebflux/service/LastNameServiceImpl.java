package com.john.springwebflux.service;

import com.john.springwebflux.business.port.out.LastNameService;
import com.john.springwebflux.business.port.out.dto.Lastname;
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
