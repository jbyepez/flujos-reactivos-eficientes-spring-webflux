package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Numerology;
import com.john.springwebflux.service.NumerologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.john.springwebflux.util.Constants.DELAY_1;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class NumerologyServiceImpl implements NumerologyService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Numerology> get(String firstName, String secondName, String lastname, LocalDate birthDate) {
        logger.info("getting numerology");
        return delayedMono(getNumerology(firstName, secondName, lastname, birthDate), DELAY_1);
    }

    private Numerology getNumerology(String firstName, String secondName, String lastname, LocalDate birthDate){
        requireNonNull(firstName);
        requireNonNull(secondName);
        requireNonNull(lastname);
        requireNonNull(birthDate);
        return new Numerology(8, 1);
    }
}
