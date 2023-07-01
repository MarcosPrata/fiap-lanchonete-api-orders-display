package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.CreateProductPort
import javax.inject.Named

@Named
class CreateProduct(
    private val createProductPort: CreateProductPort
) {
    fun execute(product: Product): Result<Product, DomainException> {
        return createProductPort.execute(product)
    }
}