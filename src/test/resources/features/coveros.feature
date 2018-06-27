# new feature
# Tags: optional
    
Feature: Navigating Coveros Website

Scenario: SecureCI Submission Message Success
    Given I have opened the Chrome browser
    When I open Coveros website
    And I click SecureCI
    And I enter in my first name as Bob
    And I enter in my Last Name as Smith
    And I enter in my EMAIL as email@email.com
    And I enter in my CoMPAny as Company
    And to download SecureCI I click submit
    Then the message should say Your message was sent successfully. Thanks.

Scenario: Verify Recent Blog Posts
    Given I have opened the Chrome browser
    When I open Coveros website
    And I click Blog
    Then the page should display Recent Blogs

Scenario: Navigate to Training Page
    Given I have opened the Chrome browser
    When I open Coveros website
    And I hover over Services Dropdown
    And I click Training
    Then the title should be Training - Coveros