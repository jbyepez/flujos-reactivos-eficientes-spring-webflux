package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Age;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.Period;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class AgeServiceImplTest {

    @InjectMocks
    private AgeServiceImpl ageService;

    @Test
    void get() {
        //given
        LocalDate birthDate = LocalDate.of(2000, 1, 1);

        //when
        Mono<Age> ageMono = ageService.get(birthDate);

        //then
        StepVerifier.create(ageMono).assertNext(age ->
                then(age.age()).isEqualTo(Period.between(requireNonNull(birthDate), LocalDate.now()).getYears())
        ).verifyComplete();
    }
}