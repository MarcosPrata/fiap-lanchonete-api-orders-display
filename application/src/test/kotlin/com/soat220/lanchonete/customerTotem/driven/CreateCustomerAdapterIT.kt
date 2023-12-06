package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.exception.CreateCustomerException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(
    locations = ["classpath:application-test2.properties"]
//            locations = ["classpath:application-test.yml"]
)
class CreateCustomerAdapterIT (
    @Autowired private val customerRepository: CustomerRepository
){

    private final val createCustomerAdapter: CreateCustomerAdapter =
        CreateCustomerAdapter(this.customerRepository);

    @Test
    fun shouldCreateCustomerSuccessful() {
        val customer = Customer(3L, "teste", "teste", "teste" );

        val result = this.createCustomerAdapter.execute(customer);

        assertThat(result).isEqualTo(Success(customer))
        assertThat(this.customerRepository.existsById(3L)).isTrue();
    }

    @Test
    fun shouldNotCreateCustomerWithoutCpf() {
        val customer = Customer(4L, "teste", "teste", null );

        val result = this.createCustomerAdapter.execute(customer);

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
        assertThat(this.customerRepository.existsById(4L)).isFalse();

    }

}