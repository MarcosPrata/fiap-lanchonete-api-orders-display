
Feature: Product

  Background: Constants
    Given BaseURL "/api/erp/products"
    And Valid product request
    And Post request to "/"

  Scenario: Create Product
    Then Should create product

  Scenario: List Products
    Given Get request to "/"
    Then Should list all products

  Scenario: Find Product by id
    When Get request to "/1"
    Then Should find Product

  Scenario: Update Product
    And change product price
    When Put request passing existing product id
    Then Product price should have new value

  Scenario: Delete Product
    Given Delete request passing existing product id
    Then Product should be deleted

