@API
@Login
Feature: Verify the Login Endpoint
  Background:
    Given I add parameter "username" with value <usernameValue>
      And I add parameter "password" with value <passwordValue>
       
  @Login
  Scenario Outline: Login using username and password
    Given I request "POST /login"
    Then The response code should be "200"
    Then The "successMessage" should have "Success"
