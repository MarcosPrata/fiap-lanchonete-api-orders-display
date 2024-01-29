package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.UpdateProductPort
import javax.inject.Named

@Named
open class UpdateProduct(
    private val updateProductPort: UpdateProductPort
) {
    open fun execute(product: Product): Result<Product, DomainException> {
        return updateProductPort.execute(product)
    }
}