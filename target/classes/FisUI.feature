Feature: Add item to eBay cart

  Scenario: Verify item can be added to Cart
    Given I open the browser
    When I navigate to "https://www.ebay.com"
    And I search for "book"
    And I click on the first book in the list
    And I add the book to the cart
    Then the cart should display the number of items as updated