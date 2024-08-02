package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Lastname;
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

@ExtendWith(MockitoExtension.class)
@ExtendWith(SoftAssertionsExtension.class)
class LastNameServiceImplTest {
    @InjectSoftAssertions
    private BDDSoftAssertions soft;
    @InjectMocks
    private LastNameServiceImpl lastNameService;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(lastNameService, "baseMillis", 0);
    }

    @Test
    void get() {
        //given
        int id = 1;

        //when
        Mono<Lastname> lastnameMono = lastNameService.get(id);

        //then
        StepVerifier.create(lastnameMono).assertNext(lastname -> {
            soft.then(lastname).isNotNull();
            soft.then(lastname.documentId()).isEqualTo(id);
            soft.then(lastname.lastName()).isNotNull();
        }).verifyComplete();
    }
}