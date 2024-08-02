package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Numerology;
import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SoftAssertionsExtension.class)
class NumerologyServiceImplTest {
    @InjectSoftAssertions
    private BDDSoftAssertions soft;
    @InjectMocks
    private NumerologyServiceImpl numerologyService;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(numerologyService, "baseMillis", 0);
    }

    @Test
    void get() {
        //given
        String firstname = "Andres";
        String secondName = "Augusto";
        String lastname = "Cardenas";
        LocalDate birthDate = LocalDate.of(2000, 5, 6);

        //when
        Mono<Numerology> numerologyMono = numerologyService.get(firstname, secondName, lastname, birthDate);

        //then
        StepVerifier.create(numerologyMono).assertNext(numerology -> {
            soft.then(numerology).isNotNull();
            soft.then(numerology.expressionNumber()).isNotNull();
            soft.then(numerology.lifePathNumber()).isNotNull();
        }).verifyComplete();
    }
}