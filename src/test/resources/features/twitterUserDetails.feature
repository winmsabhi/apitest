#Author: your.email@your.domain.com
@Pass
Feature: twitterUserDetails.feature

  Scenario: Call user_timeline api By screen_name Get the tweet with highest retweet
    Given pass "stepin_forum" and "50" to user_timeline api
    Then The hashtags are following