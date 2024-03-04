package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.FirstName;
import com.john.springwebflux.service.FirstNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Constants.DELAY_2;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class FirstNameServiceImpl implements FirstNameService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<FirstName> get(Integer id) {
        logger.info("getting firstname");
        return delayedMono(getFirstname(id), DELAY_2);
    }

    private FirstName getFirstname(Integer id){
        return new FirstName(requireNonNull(id), "Fulanito");
    }
}
