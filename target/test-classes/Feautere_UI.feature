Feature: LabCorp Assessment Tests_UI

  Scenario: TCXXXXXX_FXXXXX_USXXXXX_Validate Careers link to a specific LabCorp job listing_UI
    Given the user is on the main page
    When the user clicks Career Links
    And the user searches for 'QA Test Automation Developer'
    Then the user validates the following information
      | Job Title                  | Job Location           | Job Id   |
      | Senior QA Software Analyst | Durham, North Carolina | 21-96362 |
