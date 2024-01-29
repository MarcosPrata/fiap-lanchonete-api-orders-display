package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.FindAllCustomersPort
import javax.inject.Named

@Named
open class FindAllCustomers(
    private val findAllCustomersPort: FindAllCustomersPort
) {

    open fun execute(): Result<List<Customer>, DomainException> {
        return findAllCustomersPort.execute()
    }
}