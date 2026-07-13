@WingIt @QuickStart @Authentication
Feature: WingIt Airlines — Authentication
  Validates login functionality for the WingIt Airlines booking platform.
  Uses local HTML fixture — no external dependencies.

  Background:
    When I navigate to local fixture "wingit-airlines.html"

  @TC_WINGIT_AUTH_001
  Scenario: Successful login with valid credentials
    When I enter "TD_Email" in the "email" field
    And I enter "TD_Password" in the "password" field
    And I click the "btn-login" button
    Then I should see welcome message "TD_WelcomeMessage"
    Then I log "Authentication: Valid login successful" with status "PASS"

  @TC_WINGIT_AUTH_002
  Scenario: Login fails with invalid credentials
    When I enter "TD_Email" in the "email" field
    And I enter "TD_Password" in the "password" field
    And I click the "btn-login" button
    Then I should see error message "TD_ErrorMessage"
    Then I log "Authentication: Invalid credentials rejected" with status "PASS"

  @TC_WINGIT_AUTH_003
  Scenario: Login fails with empty email
    And I click the "btn-login" button
    Then I should see error message "TD_ErrorMessage"
    Then I log "Authentication: Empty email validation works" with status "PASS"
