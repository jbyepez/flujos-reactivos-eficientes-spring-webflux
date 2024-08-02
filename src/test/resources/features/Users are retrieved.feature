Feature: Users can be retrieved
  Scenario: clients call to GET /user
    Given a client with document "C" "123456", id 123, firstname "Pepito", middle name "Sutanito", lastname "Perez", birthdate "2004-08-27", lastname history "Tiene un origen fascinante", life path number 7 and expression number 3
    When the client calls GET /user
    Then the client receives status ok and a correct user as response body
