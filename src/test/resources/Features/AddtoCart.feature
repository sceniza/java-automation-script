@Cart
Feature: Verify Cart Functionality
  Background:
    Given I open browser
      And I am on homepage
      And Im on "Cart"
    When I click "Login"
      And I wait for the "LoginButtn" to be visible
      And I enter "#####" in "Username" field
      And I enter "#####" in "Password" field
      And I click "LoginButtn"

  @AddToCart
  Scenario: Add items in the cart
    Given I click "Product1" 
      And I click "AddtoCart"
      And I click "Product2"
      And I click "AddtoCart"
      And I should see Items Added:
        |Samsung galaxy s6|1  |
        |Nokia lumia 1520 |1  |
    When I click "RemoveItem1"
      And I should see Items Added:
        |Nokia lumia 1520 |1  |
        |Nexus 6          |1  |
    Then I click "Checkout"
      And I should see "Success"