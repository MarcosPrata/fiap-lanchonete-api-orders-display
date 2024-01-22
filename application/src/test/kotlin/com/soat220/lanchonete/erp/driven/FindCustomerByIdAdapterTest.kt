package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.driven.helper.CustomerHelper.Companion.createCustomer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.*

class FindCustomerByIdAdapterTest {

    private var customerRepository: CustomerRepository = Mockito.mock(CustomerRepository::class.java)

    private var findCustomerByIdAdapter: FindCustomerByIdAdapter = FindCustomerByIdAdapter(customerRepository)

    @Test
    fun shouldFindCustomerById() {

        `when`(customerRepository.findById(1)).thenReturn(Optional.of(createCustomer()))

        val result = findCustomerByIdAdapter.execute(1);

        assertThat(result).isExactlyInstanceOf(Success::class.java)

    }

    @Test
    fun shouldHandleFindCustomerException() {

        `when`(customerRepository.findById(1)).thenThrow(RuntimeException::class.java)

        val result = findCustomerByIdAdapter.execute(1);

        assertThat(result).isExactlyInstanceOf(Failure::class.java)

    }

}