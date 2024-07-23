package com.john.springwebflux.business;

import com.john.springwebflux.domain.User;
import com.john.springwebflux.model.*;
import com.john.springwebflux.service.*;
import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class UserBusinessBaseImpl extends UserBusinessBase{
    public UserBusinessBaseImpl(IdService idService, FirstNameService firstNameService, MiddleNameService middleNameService, LastNameService lastNameService, LastNameHistoryService lastNameHistoryService, BirthdateService birthDateService, AgeService ageService, NumerologyService numerologyService) {
        super(idService, firstNameService, middleNameService, lastNameService, lastNameHistoryService, birthDateService, ageService, numerologyService);
    }

    @Override
    public Mono<User> getUser(Character docType, String docNumber) {
        return null;
    }
}

@ExtendWith(MockitoExtension.class)
@ExtendWith(SoftAssertionsExtension.class)
class UserBusinessBaseTest {
    @InjectSoftAssertions BDDSoftAssertions soft;
    @InjectMocks private UserBusinessBaseImpl userBusiness;
    @Mock private IdService idService;
    @Mock private FirstNameService firstNameService;
    @Mock private MiddleNameService middleNameService;
    @Mock private LastNameService lastNameService;
    @Mock private LastNameHistoryService lastNameHistoryService;
    @Mock private BirthdateService birthDateService;
    @Mock private AgeService ageService;
    @Mock private NumerologyService numerologyService;

    @Test
    void addId() {
        //given
        User user = new User().setDocumentType('C').setDocumentNumber("12345");
        Id id = new Id(1, user.getDocumentType(), user.getDocumentNumber());
        given(idService.get(any(), any())).willReturn(Mono.just(id));

        //when
        Mono<User> userMono = userBusiness.addId(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getId()).isEqualTo(id.id());
        }).verifyComplete();
    }

    @Test
    void addFirstname() {
        //given
        User user = new User().setId(1);
        FirstName firstName = new FirstName(user.getId(), "Pedro");
        given(firstNameService.get(any())).willReturn(Mono.just(firstName));

        //when
        Mono<User> userMono = userBusiness.addFirstname(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getFirstName()).isEqualTo(firstName.firstName());
        }).verifyComplete();
    }

    @Test
    void addMiddleName() {
        //given
        User user = new User().setId(1);
        MiddleName middleName = new MiddleName(user.getId(), "Pablo");
        given(middleNameService.get(any())).willReturn(Mono.just(middleName));

        //when
        Mono<User> userMono = userBusiness.addMiddleName(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getSecondName()).isEqualTo(middleName.secondName());
        }).verifyComplete();
    }

    @Test
    void addLastname() {
        //given
        User user = new User().setId(1);
        Lastname lastname = new Lastname(user.getId(), "Perez");
        given(lastNameService.get(any())).willReturn(Mono.just(lastname));

        //when
        Mono<User> userMono = userBusiness.addLastname(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getLastName()).isEqualTo(lastname.lastName());
        }).verifyComplete();
    }

    @Test
    void addLastNameHistory() {
        //given
        User user = new User().setLastName("Perez");
        LastnameHistory lastnameHistory = new LastnameHistory(user.getLastName(), "Esta es mi historia");
        given(lastNameHistoryService.get(any())).willReturn(Mono.just(lastnameHistory));

        //when
        Mono<User> userMono = userBusiness.addLastNameHistory(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getLastNameHistory()).isEqualTo(lastnameHistory.history());
        }).verifyComplete();
    }

    @Test
    void addBirthdate() {
        //given
        User user = new User().setId(1);
        Birthdate birthdate = new Birthdate(user.getId(), LocalDate.of(2000, 7, 15));
        given(birthDateService.get(any())).willReturn(Mono.just(birthdate));

        //when
        Mono<User> userMono = userBusiness.addBirthdate(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getBirthDate()).isEqualTo(birthdate.birthDate());
        }).verifyComplete();
    }

    @Test
    void addAge() {
        //given
        User user = new User().setBirthDate(LocalDate.of(2000, 7, 15));
        Age age = new Age(25);
        given(ageService.get(any())).willReturn(Mono.just(age));

        //when
        Mono<User> userMono = userBusiness.addAge(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getAge()).isEqualTo(age.age());
        }).verifyComplete();
    }

    @Test
    void addNumerology() {
        //given
        User user = new User().setFirstName("Pedro").setSecondName("Pablo").setLastName("Perez").setBirthDate(LocalDate.of(2000, 7, 15));
        Numerology numerology = new Numerology(7, 7);
        given(numerologyService.get(any(), any(), any(), any())).willReturn(Mono.just(numerology));

        //when
        Mono<User> userMono = userBusiness.addNumerology(user);

        //then
        StepVerifier.create(userMono).assertNext(user1 -> {
            soft.then(user1).isSameAs(user);
            soft.then(user.getExpressionNumber()).isEqualTo(numerology.expressionNumber());
            soft.then(user.getLifePathNumber()).isEqualTo(numerology.lifePathNumber());
        }).verifyComplete();
    }
}