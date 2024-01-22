package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.driven.helper.CustomerHelper.Companion.createCustomer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FindCustomerByCpfAdapterTest {

    private var customerRepository: CustomerRepository = mock(CustomerRepository::class.java)
    private var findCustomerByCpfAdapter: FindCustomerByCpfAdapter = FindCustomerByCpfAdapter(customerRepository)

    @BeforeEach
    fun setup() {
        `when`(customerRepository.findByCpf(createCustomer().cpf)).thenReturn(createCustomer())
    }

    @Test
    fun shouldFindCustomerByCpfSuccessful() {

        val result = findCustomerByCpfAdapter.execute("000.000.000-01")

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull())
            .usingRecursiveComparison()
            .isEqualTo(createCustomer().toDomain())
    }

    @Test
    fun shouldNotFindCustomerByCpf() {

        val result = findCustomerByCpfAdapter.execute("000.000.000-02")

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull()).isNull()
    }

    @Test
    fun shouldHandleOptionalCpf() {

        val result = findCustomerByCpfAdapter.execute(null)

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull()).isNull()
    }

    @Test
    fun shouldHandleDatabaseException() {

        `when`(customerRepository.findByCpf("000.000.000-03")).thenThrow(RuntimeException::class.java)

        val result = findCustomerByCpfAdapter.execute("000.000.000-03")

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
    }

}