package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.UpdateProductPort
import javax.inject.Named

@Named
class UpdateProduct(
    private val updateProductPort: UpdateProductPort
) {
    fun execute(product: Product): Result<Product, DomainException> {
        return updateProductPort.execute(product)
    }
}