@go_rest
Feature: GoRest Users API

  @getAllUsers
  Scenario: Get All Users
    When all users are requested
    Then status code 200 is returned
    And 20 users are returned

  @CreateUser
  Scenario: Create a user
    When following user is created
      | name             | email                | gender | status |
      | Donald Trump Jr3 | donald.jr3@trump.com | Male   | Active |
    Then status code 200 is returned
    And following user response is returned
      | name             | email                | gender | status |
      | Donald Trump Jr3 | donald.jr3@trump.com | Male   | Active |

  @GetUserDetails
  Scenario: Get user details with id
    When following userID 1780 is requested
    Then status code 200 is returned
    And following user response is returned
      | name          | email            | gender | status |
      | Winner Testov | winner@gmail.com | Male   | Active |

  @DeleteUser
  Scenario: Delete a user
    When following user is created
      | name        | email         | gender | status |
      | Donald Trum | don1@trump.com | Male   | Active |
    Then status code 200 is returned
    When the user is deleted
    Then status code 200 is returned
    When the user is requested
    Then status code 200 is returned