package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.erp.driven.helper.ProductHelper.Companion.createProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*

class CreateProductAdapterTest {

    private val productRepository: ProductRepository = mock(ProductRepository::class.java)
    private val createProductAdapter: CreateProductAdapter = CreateProductAdapter(productRepository)

    @Test
    fun shouldCreateProductSuccessFull() {

        // given
        doAnswer {
            it.arguments[0]
        }.`when`(productRepository).save(any())

        // when
        val result = createProductAdapter.execute(getProductDomain())

        // then
        assertThat(result).isExactlyInstanceOf(Success::class.java)

        assertThat(result.getOrNull())
            .usingRecursiveComparison()
            .isEqualTo(getProductDomain())

        verify(productRepository, times(1)).save(any())
    }

    @Test
    fun shouldHandleCreateProductException() {

        `when`(productRepository.save(any())).thenThrow(RuntimeException::class.java)

        val result = createProductAdapter.execute(getProductDomain())

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
        assertThat(result.getOrNull()).isNull()

    }

    private fun getProductDomain() = createProduct().toDomain()

}