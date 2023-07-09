package com.soat220.lanchonete.customerTotem.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.FindProductsPort
import javax.inject.Named

@Named
class ListProducts(
    private val findProductsPort: FindProductsPort
) {
    fun execute(): Result<List<Product>, DomainException> {
        return findProductsPort.execute()
    }
}