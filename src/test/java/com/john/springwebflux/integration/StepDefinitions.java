package com.john.springwebflux.integration;

import com.john.springwebflux.domain.User;
import com.john.springwebflux.model.*;
import com.john.springwebflux.service.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureWebTestClient()
public class StepDefinitions {
    @Autowired private WebTestClient testClient;
    @MockBean private IdService idService;
    @MockBean private FirstNameService firstNameService;
    @MockBean private MiddleNameService middleNameService;
    @MockBean private LastNameService lastNameService;
    @MockBean private BirthdateService birthdateService;
    @MockBean private AgeService ageService;
    @MockBean private LastNameHistoryService lastNameHistoryService;
    @MockBean private NumerologyService numerologyService;

    private Character docType;
    private String docNumber;
    private Integer id;
    private String firstName;
    private String secondName;
    private String lastName;
    private LocalDate birthDate;
    private Integer age;
    private String lastNameHistory;
    private Integer lifePathNumber;
    private Integer expressionNumber;
    private WebTestClient.ResponseSpec responseSpec;

    @Given("a client with document {string} {string}, " +
            "id {int}, " +
            "firstname {string}, " +
            "middle name {string}, " +
            "lastname {string}, " +
            "birthdate {string}, " +
            "lastname history {string}, " +
            "life path number {int} " +
            "and expression number {int}")
    public void aClientWith(String docType, String docNumber, int id, String firstName, String secondName,
                        String lastName, String birthDate, String lastNameHistory,
                        Integer lifePathNumber, Integer expressionNumber) {
        this.docType = docType.charAt(0);
        this.docNumber = docNumber;
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.birthDate = LocalDate.parse(birthDate);
        this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
        this.lastNameHistory = lastNameHistory;
        this.lifePathNumber = lifePathNumber;
        this.expressionNumber = expressionNumber;
        givenReturnsOfServices();
    }

    private void givenReturnsOfServices(){
        given(idService.get(docType, docNumber)).willReturn(Mono.just(new Id(id, docType, docNumber)));
        given(firstNameService.get(id)).willReturn(Mono.just(new FirstName(id, firstName)));
        given(middleNameService.get(id)).willReturn(Mono.just(new MiddleName(id, secondName)));
        given(lastNameService.get(id)).willReturn(Mono.just(new Lastname(id, lastName)));
        given(birthdateService.get(id)).willReturn(Mono.just(new Birthdate(id, birthDate)));
        given(ageService.get(birthDate)).willReturn(Mono.just(new Age(age)));
        given(lastNameHistoryService.get(lastName)).willReturn(Mono.just(new LastnameHistory(lastName, lastNameHistory)));
        given(numerologyService.get(firstName, secondName, lastName, birthDate))
                .willReturn(Mono.just(new Numerology(expressionNumber, lifePathNumber)));
    }

    @When("the client calls GET \\/user")
    public void theClientCallsGETUser() {
        responseSpec = testClient.get()
                .uri("/user?doc-type={docType}&doc-number={docNumber}", docType, docNumber)
                .exchange();
    }

    @Then("the client receives status ok and a correct user as response body")
    public void theClientReceivesStatusOkAndACorrectResponseBody() {
        responseSpec.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(User.class).value(responseBody -> {
                    then(responseBody).isNotNull();
                    assertAll(
                            () -> then(responseBody.getId()).isEqualTo(id),
                            () -> then(responseBody.getDocumentType()).isEqualTo(docType),
                            () -> then(responseBody.getDocumentNumber()).isEqualTo(docNumber),
                            () -> then(responseBody.getFirstName()).isEqualTo(firstName),
                            () -> then(responseBody.getSecondName()).isEqualTo(secondName),
                            () -> then(responseBody.getLastName()).isEqualTo(lastName),
                            () -> then(responseBody.getBirthDate()).isEqualTo(birthDate),
                            () -> then(responseBody.getAge()).isEqualTo(age),
                            () -> then(responseBody.getLastNameHistory()).isEqualTo(lastNameHistory),
                            () -> then(responseBody.getLifePathNumber()).isEqualTo(lifePathNumber),
                            () -> then(responseBody.getExpressionNumber()).isEqualTo(expressionNumber)
                    );
                });
    }
}
