package com.soat220.lanchonete.erp.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result

interface CreateProductPort {
    fun execute(product: Product): Result<Product, DomainException>
}