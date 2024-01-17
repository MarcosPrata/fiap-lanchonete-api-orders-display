package stepdefinitions.erp.product

import common.RestAssuredHelper
import common.RestAssuredHelper.Companion.body
import common.RestAssuredHelper.Companion.response
import io.cucumber.java8.En
import org.junit.Assert.assertEquals

class CreateProduct : En {

    init {

        Given("BaseURL {string}") { url: String ->
            RestAssuredHelper.init(url)
        }

        Given("Valid product request") {
            body = this::class.java.classLoader.getResource("json/product-valid.json")?.readText()
        }

        When("Post request to {string}") { url: String ->
            response = RestAssuredHelper.doPost(body, url)
        }

        Then("Should create product") {
            assertEquals(200, response.statusCode)
        }
    }

}
