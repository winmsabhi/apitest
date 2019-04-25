#Author: your.email@your.domain.com
@Pass
Feature: twitterUserDetails.feature

  Scenario: Call users_show api By screen_name
    Given pass "ajay184f" to users_show api
    When Hit users_show api
    Then Verify the api response