@test
Feature: Navigation Bar on Baykartech Page

  @test
  Scenario: Test navigation bars EN
    Given main page is displayed correctly
    When user clicks on the navigation bar items
    Then user clicks on the "Main Page" navigation bar

  @test
  Scenario: Test navigation bars TR
    Given main page is displayed correctly
    When user changes the language to "Turkish" and clicks on the navigation bar items
    Then user clicks on the "Main Page" navigation bar