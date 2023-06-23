package com.soat220.lanchonete.product.port

import com.soat220.lanchonete.product.model.Product
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface CreateProductPort {
    fun execute(product: Product): Result<Product, DomainException>
}