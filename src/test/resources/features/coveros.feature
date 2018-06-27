# new feature
# Tags: optional
    
Feature: Navigating Coveros Website

#Scenario: Coveros Title Exists
#    Given I have opened the Chrome browser
#    When I open Coveros website
#    Then the title should be Coveros | Bringing together agile and security to deliver superior software

#Scenario: SecureCI Title Exists
#    Given I have opened the Chrome browser
#    When I open Coveros website
#    And I click SecureCI
#    Then the title should be SecureCI - Coveros

Scenario: SecureCI Submission Message Success
    Given I have opened the Chrome browser
    When I open Coveros website
    And I click SecureCI
    And Enter in my first name as Bob
    And I enter in my last name as Smith
    And I enter in my email as email@email.com
    And I enter in my company as Company
    And to download SecureCI I click submit
    Then the message should say Your message was sent successfully. Thanks.