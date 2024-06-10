Feature: User Login

  Scenario: Successful login to Asis with valid credentials
    Given the user navigates to the Asis login page
    When the user enters valid Asis credentials
    Then the user should be logged in to Asis

  Scenario: Unsuccessful login to Asis with invalid credentials
    Given the user navigates to the Asis login page
    When the user enters invalid Asis credentials
    Then the user should see a login error message
