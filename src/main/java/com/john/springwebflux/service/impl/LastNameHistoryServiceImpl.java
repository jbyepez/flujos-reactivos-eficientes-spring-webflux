package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.LastnameHistory;
import com.john.springwebflux.service.LastNameHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class LastNameHistoryServiceImpl extends ServiceDelays implements LastNameHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(LastNameHistoryServiceImpl.class);

    @Override
    public Mono<LastnameHistory> get(String lastname) {
        logger.info("getting lastname history");
        return delayedMono(getLastNameHistory(lastname), delay2());
    }

    private LastnameHistory getLastNameHistory(String lastname){
        return new LastnameHistory(requireNonNull(lastname), "Tiene un origen fascinante que se remonta a la Edad Media.");
    }
}
