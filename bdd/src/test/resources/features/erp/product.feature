
Feature: Product

  Background: Constants
    Given BaseURL "/api/erp/products"

  Scenario: Create Product
    Given Valid product request
    When Post request to "/"
    Then Should create product

  Scenario: List Products
    Given Get request to "/"
    Then Should list all products

  Scenario: Find Product by id
    When Get request to "/1"
    Then Should find Product
