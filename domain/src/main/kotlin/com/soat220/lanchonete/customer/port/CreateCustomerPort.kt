package com.soat220.lanchonete.customer.port

import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface CreateCustomerPort {

    fun execute(customer: Customer): Result<Customer, DomainException>
}