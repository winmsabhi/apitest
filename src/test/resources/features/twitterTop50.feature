#Author: your.email@your.domain.com
@Pass
Feature: twitterTop50HighestRetweets.feature

  Scenario: Call user_timeline api By screen_name Get the tweet with highest retweet
    Given pass "stepin_forum" and "50" to user_timeline api
    When Hit user_timeline api and get max "retweet_count" tweet among retrieved tweets
    Then Verify the user_timeline api response
