Feature: Navigating Coveros Website

Background:
    Given I open the Coveros website

#Scenario: SecureCI Submission Message Success
#    When I go to the SecureCI page
#    And I enter in my first name as Bob
#    And I enter in my last name as Smith
#    And I enter in my email as email@email.com
#    And I enter in my company as Company
#    And I download SecureCI
#    Then the SecureCI confirmation message says "Your message was sent successfully. Thanks."
#
Scenario: View Recent Blog Posts
    When I go to the Blog page
    Then the page displays Recent Blogs

Scenario: Navigate to the Training Page
    When I go to the Training page
    Then the title of the page is Training - Coveros

Scenario: Navigate to Coveros Twitter Account
    When I go to the Coveros Twitter page
    Then the title of the page is Coveros, Inc (@Coveros) | Twitter

Scenario: View the Selenified README
  When I go to the Selenified page
  And I go to the Selenified Github page
  Then the title of the page is selenified/README.md at develop · Coveros/selenified · GitHub

## Errors Occur
#Scenario: Email a Presentation
#  When I go to the Presentations page
#  And I email a presentation to rachel.mccown@coveros.com from Tester
#  Then the confirmation message says "Some Stuff Here"
#
##  Errors Occur
#Scenario: Get newest blog post date
#  When I go to the Blog page
#  Then the newest blog post is dated 1/2/3
#
##  Unimplmented
#Scenario: Check if there is Agile Training in the current month
#  When I go to the Agile Transformation page
#  And I view the training schedule for June
#  Then there should be no training available
#
## Errors Occur
#Scenario: Find the CEO of Coveros
#  When I go to the Our Team page
#  Then the CEO should be Jeff Payne
#
## Unimplemented
#Scenario: Find a specific employee on the website