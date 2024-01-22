package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.driven.helper.CustomerHelper.Companion.createCustomer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class CreateCustomerAdapterTest {

    private var customerRepository: CustomerRepository = mock(CustomerRepository::class.java)
    private var createCustomerAdapter: CreateCustomerAdapter = CreateCustomerAdapter(customerRepository)

    @Test
    fun shouldCreateCustomerSuccessful() {

        // given
        doAnswer {
            it.arguments[0]
        }.`when`(customerRepository).save(any())

        // when
        val result = createCustomerAdapter.execute(createCustomer().toDomain())

        // then
        assertThat(result).isExactlyInstanceOf(Success::class.java)

        verify(customerRepository, times(1)).save(any())
    }

    @Test
    fun shouldHandleCreateCustomerException() {

        `when`(customerRepository.save(any())).thenThrow(RuntimeException::class.java)

        val result = createCustomerAdapter.execute(createCustomer().toDomain())

        assertThat(result).isExactlyInstanceOf(Failure::class.java)

    }
}