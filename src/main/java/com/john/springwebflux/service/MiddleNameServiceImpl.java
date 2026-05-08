package com.john.springwebflux.service;

import com.john.springwebflux.business.port.out.MiddleNameService;
import com.john.springwebflux.business.port.out.dto.MiddleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.john.springwebflux.util.Utils.delayedMono;
import static java.util.Objects.requireNonNull;

@Service
public class MiddleNameServiceImpl extends ServiceDelays implements MiddleNameService {
    private static final Logger logger = LoggerFactory.getLogger(MiddleNameServiceImpl.class);

    @Override
    public Mono<MiddleName> get(Integer id) {
        logger.info("getting middle name");
        return delayedMono(getMiddleName(id), delay3());
    }

    private MiddleName getMiddleName(Integer id){
        return new MiddleName(requireNonNull(id), "Sutanito");
    }
}
