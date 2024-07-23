Feature: Users can be retrieved
  Scenario: clients call to GET /user
    Given a client with document "C" "123456"
    When the client calls GET /user
    Then the client receives status ok and a full user as response body
