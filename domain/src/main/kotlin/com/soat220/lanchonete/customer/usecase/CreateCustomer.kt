package com.soat220.lanchonete.customer.usecase

import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.customer.port.CreateCustomerPort
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result
import javax.inject.Named

@Named
class CreateCustomer(
    private val createCustomerPort: CreateCustomerPort
) {
    fun execute(customer: Customer): Result<Customer, DomainException> {
        return createCustomerPort.execute(customer)
    }
}