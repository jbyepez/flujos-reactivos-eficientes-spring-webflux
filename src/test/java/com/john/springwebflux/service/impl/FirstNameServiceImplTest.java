package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.FirstName;
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
class FirstNameServiceImplTest {
    @InjectSoftAssertions
    private BDDSoftAssertions soft;
    @InjectMocks
    private FirstNameServiceImpl firstNameService;

    @Test
    void get() {
        //given
        int id = 1;

        //when
        Mono<FirstName> firstNameMono = firstNameService.get(id);

        //then
        StepVerifier.create(firstNameMono).assertNext(firstName -> {
            soft.then(firstName).isNotNull();
            soft.then(firstName.documentId()).isEqualTo(id);
            soft.then(firstName.firstName()).isNotNull();
        }).verifyComplete();
    }
}