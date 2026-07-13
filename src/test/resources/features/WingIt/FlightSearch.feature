@WingIt @QuickStart @FlightSearch
Feature: WingIt Airlines — Flight Search
  Validates flight search functionality across multiple routes.
  Uses local HTML fixture — no external dependencies.

  Background:
    When I navigate to local fixture "wingit-airlines.html"
    And I login as "pilot@wingit.demo" with password "FlyHigh2024!"

  @TC_WINGIT_SEARCH_001
  Scenario: Search flights from New York to London
    When I enter "TD_From" in the "from-city" field
    And I enter "TD_To" in the "to-city" field
    And I click the "btn-search" button
    Then I should see "TD_FlightCount" flight results
    Then I log "Flight Search: JFK to LHR returns 3 flights" with status "PASS"

  @TC_WINGIT_SEARCH_002
  Scenario: Search flights from Tokyo to Sydney
    When I enter "TD_From" in the "from-city" field
    And I enter "TD_To" in the "to-city" field
    And I click the "btn-search" button
    Then I should see "TD_FlightCount" flight results
    Then I log "Flight Search: NRT to SYD returns 2 flights" with status "PASS"

  @TC_WINGIT_SEARCH_003
  Scenario: Search flights from Paris to Dubai
    When I enter "TD_From" in the "from-city" field
    And I enter "TD_To" in the "to-city" field
    And I click the "btn-search" button
    Then I should see "TD_FlightCount" flight results
    Then I log "Flight Search: CDG to DXB returns 4 flights" with status "PASS"

  @TC_WINGIT_SEARCH_004
  Scenario: Search with invalid route shows no results
    When I enter "TD_From" in the "from-city" field
    And I enter "TD_To" in the "to-city" field
    And I click the "btn-search" button
    Then I should see error message "TD_ErrorMessage"
    Then I log "Flight Search: Invalid route handled gracefully" with status "PASS"
