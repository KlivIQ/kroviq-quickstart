@WingIt @QuickStart @Booking
Feature: WingIt Airlines — Booking
  Validates end-to-end booking flow: search → select → confirm.
  Uses local HTML fixture — no external dependencies.

  Background:
    When I navigate to local fixture "wingit-airlines.html"
    And I login as "pilot@wingit.demo" with password "FlyHigh2024!"

  @TC_WINGIT_BOOK_001
  Scenario: Book flight from New York to London
    When I search flights from "TD_From" to "TD_To"
    And I select flight "TD_FlightNumber"
    And I enter "TD_PassengerName" in the "passenger-name" field
    And I click the "btn-confirm" button
    Then I should see confirmation "TD_ConfirmationMessage"
    Then I log "Booking: JFK-LHR booking confirmed for Jane Doe" with status "PASS"

  @TC_WINGIT_BOOK_002
  Scenario: Book flight from Tokyo to Sydney
    When I search flights from "TD_From" to "TD_To"
    And I select flight "TD_FlightNumber"
    And I enter "TD_PassengerName" in the "passenger-name" field
    And I click the "btn-confirm" button
    Then I should see confirmation "TD_ConfirmationMessage"
    Then I log "Booking: NRT-SYD booking confirmed for John Smith" with status "PASS"

  @TC_WINGIT_BOOK_003
  Scenario: Book flight from Paris to Dubai
    When I search flights from "TD_From" to "TD_To"
    And I select flight "TD_FlightNumber"
    And I enter "TD_PassengerName" in the "passenger-name" field
    And I click the "btn-confirm" button
    Then I should see confirmation "TD_ConfirmationMessage"
    Then I log "Booking: CDG-DXB booking confirmed for Alice Chen" with status "PASS"
