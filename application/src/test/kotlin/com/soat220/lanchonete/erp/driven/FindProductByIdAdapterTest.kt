package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.erp.driven.helper.ProductHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.*

class FindProductByIdAdapterTest {

    private val productRepository: ProductRepository = Mockito.mock(ProductRepository::class.java)
    private val findProductByIdAdapter: FindProductByIdAdapter = FindProductByIdAdapter(productRepository)

    @Test
    fun shouldFindCustomerById() {

        `when`(productRepository.findById(1)).thenReturn(Optional.of(ProductHelper.createProduct()))

        val result = findProductByIdAdapter.execute(1);

        assertThat(result).isExactlyInstanceOf(Success::class.java)

    }

    @Test
    fun shouldHandleFindCustomerException() {

        `when`(productRepository.findById(1)).thenThrow(RuntimeException::class.java)

        val result = findProductByIdAdapter.execute(1);

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
        assertThat(result.getOrNull()).isNull()

    }
}