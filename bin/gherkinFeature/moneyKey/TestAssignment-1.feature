#Author: parul.pat3l@gmail.com
Feature: TestAssignment-1 - Create an account Page.
  Story_ID= 1

  Scenario Outline: TestAssignment-1|AC-1 - Happy Path for Create Account Page
    Given user launched moneyKey Application
    When user clicks on "createAccount" button
    Then user is on CreateAccount page
    #And user enters "<firstName>" in "firstName" field
    #And user enters "<lastName>" in "lastName" field
    #And user enters "<email>" in "email" field
    #And user enters "<password>" in "password" field
    #And user clicks on "signupMpower" button
    #And user clicks on "create" button
    #And user is on "ChildAccount" page
    And user logs out of system

    Examples: 
      | firstName | lastName | email            | password     |
      | tester    | lastTest | tester@gmail.com | MoneyKey@123 |
