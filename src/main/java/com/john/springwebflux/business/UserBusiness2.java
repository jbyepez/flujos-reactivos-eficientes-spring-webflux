package com.john.springwebflux.business;

import com.john.springwebflux.domain.User;
import com.john.springwebflux.service.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service("UserBusiness2")
public class UserBusiness2 extends UserBusinessBase {

    public UserBusiness2(IdService idService, FirstNameService firstNameService, MiddleNameService middleNameService, LastNameService lastNameService, LastNameHistoryService lastNameHistoryService, BirthdateService birthDateService, AgeService ageService, NumerologyService numerologyService) {
        super(idService, firstNameService, middleNameService, lastNameService, lastNameHistoryService, birthDateService, ageService, numerologyService);
    }

    @Override
    public Mono<User> getUser(Character docType, String docNumber) {
        return Mono.just(new User().setDocumentType(docType).setDocumentNumber(docNumber))
                .flatMap(this::addId)
                .flatMap(user -> Mono
                        .zip(addFirstname(user), addMiddleName(user), addLastname(user), addBirthdate(user))
                        .map(Tuple2::getT1))
                .flatMap(user -> Mono
                        .zip(addLastNameHistory(user), addAge(user), addNumerology(user))
                        .map(Tuple2::getT1));
    }
}
