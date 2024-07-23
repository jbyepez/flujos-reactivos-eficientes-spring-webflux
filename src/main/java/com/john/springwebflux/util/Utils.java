package com.john.springwebflux.util;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.Callable;

public class Utils {

    private Utils(){}

    public static <T> Mono<T> parallelizedMono(Callable<T> supplier){
        return Mono.fromCallable(supplier)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public static <T> Mono<T> delayedMono(T value, Duration delay){
        return reactiveDelay(value, delay);
        //return parallelizedMono(() -> blockingDelay(value, delay));
    }

    public static <T> Mono<T> reactiveDelay(T value, Duration delay){
        return Mono.just(value).delayElement(delay);
    }

    public static <T> T blockingDelay(T value, Duration delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
