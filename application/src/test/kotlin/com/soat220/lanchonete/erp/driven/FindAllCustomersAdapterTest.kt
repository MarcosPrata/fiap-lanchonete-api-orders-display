package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Customer
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.erp.driven.helper.CustomerHelper.Companion.createCustomer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class FindAllCustomersAdapterTest {

    private var customerRepository: CustomerRepository = mock(CustomerRepository::class.java)

    private var findAllCustomersAdapter: FindAllCustomersAdapter = FindAllCustomersAdapter(customerRepository)

    @Test
    fun shouldFindAllCustomersSuccessful() {

        `when`(customerRepository.findAll()).thenReturn(createCustomersList())

        val result = findAllCustomersAdapter.execute()

        assertThat(result).isExactlyInstanceOf(Success::class.java)
    }

    @Test
    fun shouldHandleDomainException() {

        `when`(customerRepository.findAll()).thenThrow(DomainException::class.java)

        val result = findAllCustomersAdapter.execute()
        assertThat(result).isExactlyInstanceOf(Failure::class.java)

    }

    private fun createCustomersList(): List<Customer> {
        return listOf(createCustomer(id = 1), createCustomer(id = 2), createCustomer(id = 3))
    }

}