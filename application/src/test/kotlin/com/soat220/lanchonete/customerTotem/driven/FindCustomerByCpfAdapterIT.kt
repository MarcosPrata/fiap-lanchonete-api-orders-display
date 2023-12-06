package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Customer
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(
    locations = ["classpath:application-test2.properties"]
)
class FindCustomerByCpfAdapterIT(
    @Autowired private val customerRepository: CustomerRepository
) {

    private final val findCustomerByCpfAdapter: FindCustomerByCpfAdapter =
        FindCustomerByCpfAdapter(this.customerRepository);

    @Test
    fun shouldFindCustomerByCpfSuccessful() {
        val customer = CustomerHelper.createCustomer(3L, "cpf");
        this.customerRepository.save(Customer.fromDomain(customer));

        assertThat(this.findCustomerByCpfAdapter.execute("cpf"))
            .isEqualTo(Success(customer));
    }

    @Test
    fun shouldNotFindCustomerWhenNonExistingCpf() {
        val customer = CustomerHelper.createCustomer(4L, "cpf");

        this.customerRepository.save(Customer.fromDomain(customer));

        val result = this.findCustomerByCpfAdapter.execute("novo cpf")

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
        assertThat(result).withFailMessage { "Result must not be null!" }

    }

    @Test
    fun shouldReturnSuccessWhenCpfIsNull() {
        assertThat(this.findCustomerByCpfAdapter.execute(null))
            .isEqualTo(Success(null));
    }

}