package com.soat220.lanchonete.product.gateway

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.product.model.Product
import com.soat220.lanchonete.result.Result

interface CreateProductGateway {
    fun execute(product: Product): Result<Product, DomainException>
}