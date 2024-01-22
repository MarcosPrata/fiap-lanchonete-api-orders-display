package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.erp.driven.helper.ProductHelper.Companion.createProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*

class DeleteProductByIdAdapterTest {

    private val productRepository: ProductRepository = mock(ProductRepository::class.java)
    private val deleteProductByIdAdapter: DeleteProductByIdAdapter = DeleteProductByIdAdapter(productRepository)

    @Test
    fun shouldDeleteProductSuccessful() {

        // given
        val product = createProduct()
        `when`(productRepository.findById(1)).thenReturn(Optional.of(product))

        // when
        deleteProductByIdAdapter.execute(1)
        product.deleted = true

        // then
        verify(productRepository, times(1)).save(product)
    }

    @Test
    fun shouldThrowProductNotFoundException() {

        `when`(productRepository.findById(1)).thenReturn(Optional.empty())

        val result = deleteProductByIdAdapter.execute(1)

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
        verify(productRepository, never()).save(any())
    }

}