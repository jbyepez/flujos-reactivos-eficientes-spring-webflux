package com.john.springwebflux.business;

import com.john.springwebflux.domain.User;
import com.john.springwebflux.service.*;
import reactor.core.publisher.Mono;

public abstract class UserBusinessBase implements UserBusiness {

    final IdService idService;
    final FirstNameService firstNameService;
    final MiddleNameService middleNameService;
    final LastNameService lastNameService;
    final LastNameHistoryService lastNameHistoryService;
    final BirthdateService birthDateService;
    final AgeService ageService;
    final NumerologyService numerologyService;

    public UserBusinessBase(IdService idService, FirstNameService firstNameService, MiddleNameService middleNameService, LastNameService lastNameService, LastNameHistoryService lastNameHistoryService, BirthdateService birthDateService, AgeService ageService, NumerologyService numerologyService) {
        this.idService = idService;
        this.firstNameService = firstNameService;
        this.middleNameService = middleNameService;
        this.lastNameService = lastNameService;
        this.lastNameHistoryService = lastNameHistoryService;
        this.birthDateService = birthDateService;
        this.ageService = ageService;
        this.numerologyService = numerologyService;
    }

    Mono<User> addId(User user) {
        return idService.get(user.getDocumentType(), user.getDocumentNumber())
                .map(id -> user.setId(id.id()));
    }

    Mono<User> addFirstname(User user) {
        return firstNameService.get(user.getId())
                .map(firstName -> user.setFirstName(firstName.firstName()));
    }

    Mono<User> addMiddleName(User user) {
        return middleNameService.get(user.getId())
                .map(secondName -> user.setSecondName(secondName.secondName()));
    }

    Mono<User> addLastname(User user) {
        return lastNameService.get(user.getId())
                .map(lastName -> user.setLastName(lastName.lastName()));
    }

    Mono<User> addLastNameHistory(User user) {
        return lastNameHistoryService.get(user.getLastName())
                .map(history -> user.setLastNameHistory(history.history()));
    }

    Mono<User> addBirthdate(User user) {
        return birthDateService.get(user.getId())
                .map(birthDate -> user.setBirthDate(birthDate.birthDate()));
    }

    Mono<User> addAge(User user) {
        return ageService.get(user.getBirthDate())
                .map(age -> user.setAge(age.age()));
    }

    Mono<User> addNumerology(User user) {
        return numerologyService.get(user.getFirstName(), user.getSecondName(), user.getLastName(), user.getBirthDate())
                .map(numerology -> user
                        .setLifePathNumber(numerology.lifePathNumber())
                        .setExpressionNumber(numerology.expressionNumber()));
    }
}
