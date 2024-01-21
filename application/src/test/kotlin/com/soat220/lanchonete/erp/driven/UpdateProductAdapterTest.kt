package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Product
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.erp.driven.helper.ProductHelper.Companion.createProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.*

class UpdateProductAdapterTest {

    private val productRepository: ProductRepository = Mockito.mock(ProductRepository::class.java)
    private val updateProductAdapter: UpdateProductAdapter = UpdateProductAdapter(productRepository)

    @Test
    fun shouldUpdateProductSuccessful() {

        updateProductAdapter.execute(createProduct().toDomain())

        verify(productRepository, times(1)).save(any(Product::class.java))

    }

    @Test
    fun shouldHandleUpdateProductException() {

        `when`(productRepository.save(any(Product::class.java))).thenThrow(IllegalArgumentException::class.java)

        assertThat(updateProductAdapter.execute(createProduct().toDomain()))
            .isExactlyInstanceOf(Failure::class.java)

    }
}