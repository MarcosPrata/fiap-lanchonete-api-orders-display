package com.soat220.lanchonete.customerTotem.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.customerTotem.port.FindCustomerByCpfPort
import javax.inject.Named

@Named
class FindCustomerByCpf(
    private val findCustomerByCpfPort: FindCustomerByCpfPort
) {

    fun execute(cpf: String): Result<Customer?, DomainException> {
        return findCustomerByCpfPort.execute(cpf)
    }
}