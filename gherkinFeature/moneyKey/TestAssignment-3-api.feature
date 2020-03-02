#Author: parul.pat3l@gmail.com
Feature: TestAssignment-3 - Test Employee REST API testing
  Story_ID= 3

  Scenario: TestAssignment-3|AC-1 - Add Record API Test
    Given user set POST Employee service api endpoint
    When user set request Header
    And user Send a POST HTTP Request
    Then user recieves valid Response
    
  Scenario: TestAssignment-3|AC-2 - Update Record API Test
    Given user set PUT Employee service api endpoint
    When user set Update request Body
    And user set Update request Body
    Then user recieves valid HTTP Response Code 200

  Scenario: TestAssignment-3|AC-3 - Get Record API Test
    Given user set GET Employee service api endpoint
    When user set request Header
    And user Send GET HTTP Request and verifies success code

  Scenario: TestAssignment-3|AC-4 - Delete Record API Test
    Given user set DELETE Employee service api endpoint
    And user Send a DELETE HTTP Request
    Then user recieves valid HTTP Response Code 200

