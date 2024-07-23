package com.john.springwebflux.service.impl;

import com.john.springwebflux.model.LastnameHistory;
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
class LastNameHistoryServiceImplTest {
    @InjectSoftAssertions
    private BDDSoftAssertions soft;
    @InjectMocks
    private LastNameHistoryServiceImpl lastNameHistoryService;

    @Test
    void get() {
        //given
        String lastName = "Correa";

        //when
        Mono<LastnameHistory> lastnameHistoryMono = lastNameHistoryService.get(lastName);

        //then
        StepVerifier.create(lastnameHistoryMono).assertNext(lastnameHistory -> {
            soft.then(lastnameHistory).isNotNull();
            soft.then(lastnameHistory.lastName()).isEqualTo(lastName);
            soft.then(lastnameHistory.history()).isNotNull();
        }).verifyComplete();
    }
}