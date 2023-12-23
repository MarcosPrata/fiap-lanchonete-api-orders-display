package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.driven.support.CustomerSupport
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CreateCustomerAdapterIT (
    @Autowired private val customerRepository: CustomerRepository
){

    private final val createCustomerAdapter: CreateCustomerAdapter =
        CreateCustomerAdapter(this.customerRepository);

    @Test
    fun shouldCreateCustomerSuccessful() {
        val customer = CustomerSupport.createCustomer(null);

        val result = this.createCustomerAdapter.execute(customer);

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(this.customerRepository.existsById(3L)).isTrue()

        this.customerRepository.findById(3L).ifPresent {

            assertThat(it).hasFieldOrPropertyWithValue("name", "testname")

        }
    }

    @Test
    fun shouldNotCreateCustomerWithoutCpf() {
        val customer = CustomerSupport.createCustomerWithCpf(4L, null);

        val result = this.createCustomerAdapter.execute(customer);

        assertThat(result).isExactlyInstanceOf(Failure::class.java)

    }

}