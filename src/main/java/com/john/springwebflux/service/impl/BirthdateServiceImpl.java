package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Birthdate;
import com.john.springwebflux.service.BirthdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class BirthdateServiceImpl extends ServiceDelays implements BirthdateService {
    private static final Logger logger = LoggerFactory.getLogger(BirthdateServiceImpl.class);

    @Override
    public Mono<Birthdate> get(Integer id) {
        logger.info("getting birth date");
        return delayedMono(getBirthdate(id), delay1());
    }

    private Birthdate getBirthdate(Integer id){
        return new Birthdate(requireNonNull(id), LocalDate.of(2005, 6, 15));
    }
}
