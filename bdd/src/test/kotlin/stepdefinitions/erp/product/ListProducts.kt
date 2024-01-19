package stepdefinitions.erp.product

import common.RestAssuredHelper.Companion.doGet
import common.RestAssuredHelper.Companion.response
import io.cucumber.java8.En
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class ListProducts: En {

    init {

        Given("Get request to {string}") { url: String ->
            response = doGet(url)
        }

        Then("Should list all products") {
            assertEquals(200, response.statusCode)

            val jsonPathEvaluator = response.jsonPath()

            val products = jsonPathEvaluator.get<List<String>>()
            assertTrue(products.isNotEmpty())
        }

    }

}