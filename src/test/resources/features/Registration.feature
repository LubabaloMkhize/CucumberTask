Feature: Sign Up and Login on Ndosi Automation

  Scenario Outline: Navigate to Learning Materials, Sign Up, and Login
    Given I am on the Ndosi Automation homepage
    When I click on the "Learning Materials" tab
    And I click on the "Sign Up" button
    And I fill in the registration form with valid details
      | Field           | Value            |
      | FirstName       | <FirstName>      |
      | LastName        | <LastName>       |
      | Email           | <Email>          |
      | Password        | <Password>       |
      | ConfirmPassword | <ConfirmPassword> |
    And I submit the registration form
    Then I should see a confirmation message "Registration successful! Please login with your credentials."
    When I enter my password to log in
    And I submit the login form
    Then I should be logged in and see the page title "Practice Assessments"

  Examples:
    | FirstName | LastName | Email            | Password | ConfirmPassword |
    | Michael   | Todd     | MT@example.com   | Pass1234 | Pass1234        |
    | John      | Doe      | john.doe@test.com| Test1234 | Test1234        |
