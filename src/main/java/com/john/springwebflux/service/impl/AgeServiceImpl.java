package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Age;
import com.john.springwebflux.service.AgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;

import static com.john.springwebflux.util.Constants.DELAY_3;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class AgeServiceImpl implements AgeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Age> get(LocalDate birthDate) {
        logger.info("getting age");
        return delayedMono(getAge(birthDate), DELAY_3);
    }

    private Age getAge(LocalDate birthDate){
        return new Age(Period.between(requireNonNull(birthDate), LocalDate.now()).getYears());
    }
}
