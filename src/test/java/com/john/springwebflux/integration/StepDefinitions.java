package com.john.springwebflux.integration;

import com.john.springwebflux.domain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertAll;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureWebTestClient(timeout = "36000")
public class StepDefinitions {
    @Autowired private WebTestClient testClient;

    private String docType;
    private String docNumber;
    private WebTestClient.ResponseSpec exchange;

    @Given("a client with document {string} {string}")
    public void aClient(String docType, String docNumber) {
        this.docType = docType;
        this.docNumber = docNumber;
    }

    @When("the client calls GET \\/user")
    public void theClientCallsGETUser() {
        exchange = testClient.get()
                .uri("/user?doc-type={docType}&doc-number={docNumber}", docType, docNumber)
                .exchange();
    }

    @Then("the client receives status ok and a full user as response body")
    public void theClientReceivesStatusOkAndAFullResponseBody() {
        exchange.expectStatus().isOk()
                .expectBody(User.class).value(responseBody -> {
                    then(responseBody).isNotNull();
                    assertAll(
                            () -> then(responseBody.getId()).isNotNull(),
                            () -> then(responseBody.getDocumentType()).asString().isEqualTo(docType),
                            () -> then(responseBody.getDocumentNumber()).isEqualTo(docNumber),
                            () -> then(responseBody.getFirstName()).isNotNull(),
                            () -> then(responseBody.getSecondName()).isNotNull(),
                            () -> then(responseBody.getLastName()).isNotNull(),
                            () -> then(responseBody.getBirthDate()).isNotNull(),
                            () -> then(responseBody.getAge()).isNotNull(),
                            () -> then(responseBody.getLastNameHistory()).isNotNull(),
                            () -> then(responseBody.getLifePathNumber()).isNotNull(),
                            () -> then(responseBody.getExpressionNumber()).isNotNull()
                    );
                });
    }
}
