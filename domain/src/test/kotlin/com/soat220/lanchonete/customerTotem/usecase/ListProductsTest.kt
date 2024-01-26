package com.soat220.lanchonete.customerTotem.usecase

import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.erp.port.FindProductsPort
import com.soat220.lanchonete.helper.ProductHelper.Companion.createProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ListProductsTest {

    private val findProductsPort = mock(FindProductsPort::class.java)
    private val listProducts = ListProducts(findProductsPort)

    @Test
    fun shouldOnlyListNotDeletedProducts() {

        `when`(findProductsPort.execute()).thenReturn(Success(getProducts()))

        val result = listProducts.execute()

        assertThat(result.getOrNull()).asList().isNotEmpty().hasSize(2)

    }

    private fun getProducts(): List<Product> {
        return listOf(
            createProduct(),
            createProduct(id = 2),
            createProduct(id = 3, deleted = true)
        )
    }
}