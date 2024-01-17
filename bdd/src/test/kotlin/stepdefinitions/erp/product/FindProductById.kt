package stepdefinitions.erp.product

import common.RestAssuredHelper.Companion.response
import io.cucumber.java8.En
import org.junit.Assert.assertEquals

class FindProductById: En {

    init {

        Then("Should find Product") {
            assertEquals(200, response.statusCode)

            val jsonPathEvaluator = response.jsonPath()

            val productId = jsonPathEvaluator.get<Int>("id")
            assertEquals(1, productId)
        }

    }
}