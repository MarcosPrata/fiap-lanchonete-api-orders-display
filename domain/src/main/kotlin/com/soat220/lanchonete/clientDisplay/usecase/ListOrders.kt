package com.soat220.lanchonete.clientDisplay.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.erp.port.CreateProductPort
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result
import javax.inject.Named

@Named
class ListOrders(
    private val createProductPort: CreateProductPort
) {
    fun execute(product: Product): Result<Product, DomainException> {
        return createProductPort.execute(product)
    }
}