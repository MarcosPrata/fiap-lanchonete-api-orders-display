package com.soat220.lanchonete.customer.usecase

import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.customer.port.FindCustomerByCpfPort
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result
import javax.inject.Named

@Named
class FindCustomerByCpf(
    private val findCustomerByCpfPort: FindCustomerByCpfPort
) {

    fun execute(cpf: String): Result<Customer, DomainException> {
        return findCustomerByCpfPort.execute(cpf)
    }
}