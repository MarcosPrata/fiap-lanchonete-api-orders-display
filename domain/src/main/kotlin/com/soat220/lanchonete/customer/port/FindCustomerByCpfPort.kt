package com.soat220.lanchonete.customer.port

import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface FindCustomerByCpfPort {

    fun execute(cpf: String): Result<Customer, DomainException>
}