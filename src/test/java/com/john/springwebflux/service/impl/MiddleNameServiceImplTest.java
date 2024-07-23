package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.MiddleName;
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
class MiddleNameServiceImplTest {
    @InjectSoftAssertions
    private BDDSoftAssertions soft;
    @InjectMocks
    private MiddleNameServiceImpl middleNameService;

    @Test
    void get() {
        //given
        int id = 1;

        //when
        Mono<MiddleName> middleNameMono = middleNameService.get(id);

        //then
        StepVerifier.create(middleNameMono).assertNext(middleName -> {
            soft.then(middleName).isNotNull();
            soft.then(middleName.documentId()).isEqualTo(id);
            soft.then(middleName.secondName()).isNotNull();
        }).verifyComplete();
    }
}