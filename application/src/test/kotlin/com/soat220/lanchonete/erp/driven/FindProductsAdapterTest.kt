package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Product
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.erp.driven.helper.ProductHelper.Companion.createProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class FindProductsAdapterTest {

    private val productRepository: ProductRepository = mock(ProductRepository::class.java)
    private val findProductsAdapter: FindProductsAdapter = FindProductsAdapter(productRepository)

    @Test
    fun shouldFindProductsSuccessful() {

        `when`(productRepository.findAll()).thenReturn(createProductsList())

        val result = findProductsAdapter.execute()

        assertThat(result).isExactlyInstanceOf(Success::class.java)

        assertThat(result.getOrNull())
            .asList()
            .isNotEmpty()
            .hasSize(3)
    }

    @Test
    fun shouldHandleRepositoryException() {
        `when`(productRepository.findAll()).thenThrow(RuntimeException::class.java)

        val result = findProductsAdapter.execute()

        assertThat(result).isExactlyInstanceOf(Failure::class.java)

        assertThat(result.getOrNull()).isNull()
    }

    private fun createProductsList(): List<Product> {
        return listOf(
            createProduct(),
            createProduct(id = 2),
            createProduct(id = 3)
        )
    }
}