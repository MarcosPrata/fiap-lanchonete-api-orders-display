package stepdefinitions.erp.product

import common.RestAssuredHelper.Companion.currentId
import common.RestAssuredHelper.Companion.doDelete
import common.RestAssuredHelper.Companion.response
import io.cucumber.java8.En
import io.cucumber.java8.PendingException
import org.junit.Assert
import org.junit.Assert.assertEquals

class DeleteProduct: En {

    init {

        Given("Delete request passing existing product id") {

            response = doDelete("/$currentId")

        }
        Then("Product should be deleted") {
            assertEquals(200, response.statusCode)
        }

    }
}