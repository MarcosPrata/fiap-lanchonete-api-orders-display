package com.soat220.lanchonete.product.usecase

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.product.gateway.CreateProductGateway
import com.soat220.lanchonete.product.model.Product
import com.soat220.lanchonete.result.Result

class CreateMailType(
    private val createProductGateway: CreateProductGateway
) {
    fun execute(product: Product): Result<Product, DomainException> {
        return createProductGateway.execute(product)
    }
}