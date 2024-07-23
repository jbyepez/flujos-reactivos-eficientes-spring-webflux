package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Birthdate;
import com.john.springwebflux.service.BirthdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.john.springwebflux.util.Constants.DELAY_1;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class BirthdateServiceImpl implements BirthdateService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Birthdate> get(Integer id) {
        logger.info("getting birth date");
        return delayedMono(getBirthdate(id), DELAY_1);
    }

    private Birthdate getBirthdate(Integer id){
        return new Birthdate(requireNonNull(id), LocalDate.of(2005, 6, 15));
    }
}
