package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Id;
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
class IdServiceImplTest {
    @InjectSoftAssertions
    private BDDSoftAssertions soft;
    @InjectMocks
    private IdServiceImpl idService;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(idService, "baseMillis", 0);
    }

    @Test
    void get() {
        //given
        char type = 'C';
        String number = "12345";

        //when
        Mono<Id> idMono = idService.get(type, number);

        //then
        StepVerifier.create(idMono).assertNext(id -> {
            soft.then(id).isNotNull();
            soft.then(id.type()).isEqualTo(type);
            soft.then(id.number()).isEqualTo(number);
            soft.then(id.id()).isNotNull();
        }).verifyComplete();
    }
}