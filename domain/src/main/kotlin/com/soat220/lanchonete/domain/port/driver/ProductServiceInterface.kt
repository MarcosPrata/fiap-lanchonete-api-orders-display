package com.soat220.lanchonete.domain.port.driver

import com.soat220.lanchonete.domain.model.Product
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface ProductServiceInterface {

    fun save(product: Product): Result<Product, DomainException>
}