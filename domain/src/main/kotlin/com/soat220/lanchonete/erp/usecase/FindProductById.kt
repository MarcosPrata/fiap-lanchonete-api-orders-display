package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.FindProductByIdPort
import javax.inject.Named

@Named
class FindProductById(
    private val findProductByIdPort: FindProductByIdPort
) {
    fun execute(productId: Long): Result<Product?, DomainException> {
        return findProductByIdPort.execute(productId)
    }
}