package stepdefinitions.erp.product

import common.RestAssuredHelper.Companion.body
import common.RestAssuredHelper.Companion.currentId
import common.RestAssuredHelper.Companion.doPut
import common.RestAssuredHelper.Companion.response
import io.cucumber.java8.En
import org.junit.Assert.assertEquals

class UpdateProduct: En {

    init {
        Given("change product price") {
            val productJson = this::class.java.classLoader.getResource("json/product-valid.json")?.readText()
            body = productJson?.replace("10.5", "21.0")
        }

        When("Put request passing existing product id") {
            response = doPut("$currentId", body)
        }

        Then("Product price should have new value") {

            assertEquals(200, response.statusCode)

            val jsonPathEvaluator = response.jsonPath()

            val productPrice: Float = jsonPathEvaluator.get("price")
            assertEquals(21.0F, productPrice)

        }
    }
}