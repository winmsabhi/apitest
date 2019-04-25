#Author: your.email@your.domain.com
@Pass
Feature: twitterTop50.feature

  Scenario: Call user_timeline api By screen_name
    Given pass "stepin_forum" and "4 " to user_timeline api
    When Hit user_timeline api
    Then Verify the api response