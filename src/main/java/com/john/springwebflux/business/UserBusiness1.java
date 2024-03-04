package com.john.springwebflux.business;

import com.john.springwebflux.domain.User;
import com.john.springwebflux.service.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("UserBusiness1")
public class UserBusiness1 extends UserBusinessBase {

    public UserBusiness1(IdService idService, FirstNameService firstNameService, MiddleNameService middleNameService, LastNameService lastNameService, LastNameHistoryService lastNameHistoryService, BirthdateService birthDateService, AgeService ageService, NumerologyService numerologyService) {
        super(idService, firstNameService, middleNameService, lastNameService, lastNameHistoryService, birthDateService, ageService, numerologyService);
    }

    @Override
    public Mono<User> getUser(Character docType, String docNumber) {
        return Mono.just(new User().setDocumentType(docType).setDocumentNumber(docNumber))
                .flatMap(this::addId)
                .flatMap(this::addFirstname)
                .flatMap(this::addMiddleName)
                .flatMap(this::addLastname)
                .flatMap(this::addLastNameHistory)
                .flatMap(this::addBirthdate)
                .flatMap(this::addAge)
                .flatMap(this::addNumerology);
    }
}
