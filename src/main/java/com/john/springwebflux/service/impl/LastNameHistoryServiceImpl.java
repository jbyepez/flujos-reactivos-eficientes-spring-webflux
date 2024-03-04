package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.LastnameHistory;
import com.john.springwebflux.service.LastNameHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Constants.DELAY_2;
import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class LastNameHistoryServiceImpl implements LastNameHistoryService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<LastnameHistory> get(String lastname) {
        logger.info("getting lastname history");
        return delayedMono(getLastNameHistory(lastname), DELAY_2);
    }

    private LastnameHistory getLastNameHistory(String lastname){
        return new LastnameHistory(requireNonNull(lastname), "Tiene un origen fascinante que se remonta a la Edad Media.");
    }
}
