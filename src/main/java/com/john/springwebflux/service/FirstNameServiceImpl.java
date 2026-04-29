package com.john.springwebflux.service;

import com.john.springwebflux.service.dto.FirstName;
import com.john.springwebflux.business.port.out.FirstNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class FirstNameServiceImpl extends ServiceDelays implements FirstNameService {
    private static final Logger logger = LoggerFactory.getLogger(FirstNameServiceImpl.class);

    @Override
    public Mono<FirstName> get(Integer id) {
        logger.info("getting firstname");
        return delayedMono(getFirstname(id), delay2());
    }

    private FirstName getFirstname(Integer id){
        return new FirstName(requireNonNull(id), "Fulanito");
    }
}
