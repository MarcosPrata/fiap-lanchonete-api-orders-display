package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.FindCustomerByIdPort
import javax.inject.Named

@Named
open class FindCustomerById(
    private val findCustomerByIdPort: FindCustomerByIdPort
) {

    open fun execute(customerId: Long): Result<Customer?, DomainException> {
        return findCustomerByIdPort.execute(customerId)
    }
}