Feature: LabCorp Assessment Tests_API

  Scenario: TCXXXXXX_FXXXXX_USXXXXX_Validate POST request with below information_API
    Given the user creates a POST request with below info and verifies the response
      | createdAt  | employee_firstname | employee_lastname | employee_phonenumbe | ademployee_emaildress   | citemployee_addressy   | stateemployee_dev_level   | employee_gender   | employee_hire_date       | employee_onleave | tech_stack | project |
      | 1631825833 | TestData12345      | TestData12345     | 264-783-9453        | ademployee_emaildress 1 | citemployee_addressy 1 | stateemployee_dev_level 1 | employee_gender 1 | 2025-10-31T16:35:45.426Z | true             |            |         |

