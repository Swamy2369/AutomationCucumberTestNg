Feature: Select a product and add to cart

  @demo
  Scenario: Select products from the whishlist and add to cart
    Given I opens the Application
    Then I add different products to my wish list
      | numberOfProducts | 4 |
    When I view my wishlist table
    Then I find total four selected items in my wishlist
    When I search for lowest price product
    And I am able to add the lowest price item to my cart
    Then I am able to verify the item in my cart
