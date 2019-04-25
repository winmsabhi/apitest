#Author: your.email@your.domain.com
@Pass
Feature: twitterTop50.feature

  Scenario: Call user_timeline api By screen_name
    Given pass "stepin_forum" and "50" to user_timeline api
    When Hit user_timeline api and get max Retweeted tweet "retweet_count" among retrieved tweets
    Then Verify the api response