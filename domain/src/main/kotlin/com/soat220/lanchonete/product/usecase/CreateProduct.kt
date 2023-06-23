package com.soat220.lanchonete.product.usecase

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.product.port.CreateProductPort
import com.soat220.lanchonete.product.model.Product
import com.soat220.lanchonete.result.Result
import javax.inject.Named

@Named
class CreateProduct(
    private val createProductPort: CreateProductPort
) {
    fun execute(product: Product): Result<Product, DomainException> {
        return createProductPort.execute(product)
    }
}
