Feature: Navigating Coveros Website

Background:
    Given I open the Coveros website

Scenario: SecureCI Submission Message Success
    When I go to the SecureCI page
    And I enter in my first name as Bob
    And I enter in my last name as Smith
    And I enter in my email as email@email.com
    And I enter in my company as Company
    And I download SecureCI
    Then the SecureCI confirmation message says "Your message was sent successfully. Thanks."

Scenario: View Recent Blog Posts
    When I go to the Blog page
    Then the page displays Recent Blogs

Scenario: Navigate to the Training Page
    When I go to the Training page
    Then the title of the page is Training - Coveros

Scenario: Navigate to Coveros Twitter Account
    When I go to the Coveros Twitter page
    Then the title of the page is Coveros, Inc (@Coveros) | Twitter
