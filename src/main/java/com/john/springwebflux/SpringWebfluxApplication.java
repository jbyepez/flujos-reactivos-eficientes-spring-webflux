package com.john.springwebflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class SpringWebfluxApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringWebfluxApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SpringWebfluxApplication.class, args);

        logger.info("Tamaño del pool de threads: {}", Schedulers.DEFAULT_BOUNDED_ELASTIC_SIZE);
		logger.info("Tamaño de la cola de espera del pool de threads: {}", Schedulers.DEFAULT_BOUNDED_ELASTIC_QUEUESIZE);
		logger.info("Virtual threads: {}", Schedulers.DEFAULT_BOUNDED_ELASTIC_ON_VIRTUAL_THREADS);
	}

}
