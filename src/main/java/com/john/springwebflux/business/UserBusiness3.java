package com.john.springwebflux.business;

import com.john.springwebflux.domain.User;
import com.john.springwebflux.service.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service("UserBusiness3")
public class UserBusiness3 extends UserBusinessBase {

    public UserBusiness3(IdService idService, FirstNameService firstNameService, MiddleNameService middleNameService, LastNameService lastNameService, LastNameHistoryService lastNameHistoryService, BirthdateService birthDateService, AgeService ageService, NumerologyService numerologyService) {
        super(idService, firstNameService, middleNameService, lastNameService, lastNameHistoryService, birthDateService, ageService, numerologyService);
    }

    @Override
    public Mono<User> getUser(Character docType, String docNumber) {
        Mono<User> addedDocument = Mono.just(new User().setDocumentType(docType).setDocumentNumber(docNumber));
        Mono<User> addedId = addedDocument.flatMap(this::addId).cache();
        Mono<User> addedFirstName = addedId.flatMap(this::addFirstname);
        Mono<User> addedMiddleName = addedId.flatMap(this::addMiddleName);
        Mono<User> addedLastname = addedId.flatMap(this::addLastname).cache();
        Mono<User> addedLastNameHistory = addedLastname.flatMap(this::addLastNameHistory);
        Mono<User> addedBirthdate = addedId.flatMap(this::addBirthdate).cache();
        Mono<User> addedAge = addedBirthdate.flatMap(this::addAge);
        Mono<User> addedNumerology = Mono.zip(addedBirthdate, addedFirstName, addedMiddleName, addedLastname)
                .map(Tuple2::getT1).flatMap(this::addNumerology);
        Mono<User> result = Mono.zip(addedAge, addedNumerology, addedLastNameHistory)
                .map(Tuple2::getT1);
        return result;
    }
}
