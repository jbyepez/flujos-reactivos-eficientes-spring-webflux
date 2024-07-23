package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.Birthdate;
import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SoftAssertionsExtension.class)
class BirthdateServiceImplTest {
    @InjectSoftAssertions
    private BDDSoftAssertions soft;
    @InjectMocks
    private BirthdateServiceImpl birthdateService;

    @Test
    void get() {
        //given
        int id = 1;

        //when
        Mono<Birthdate> birthdateMono = birthdateService.get(id);

        //then
        StepVerifier.create(birthdateMono).assertNext(birthdate -> {
            soft.then(birthdate.documentId()).isEqualTo(id);
            soft.then(birthdate.birthDate()).isNotNull();
        }).verifyComplete();
    }
}