package com.soat220.lanchonete.erp.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.erp.port.DeleteProductByIdPort
import javax.inject.Named

@Named
class DeleteProduct(
    private val deleteProductByIdPort: DeleteProductByIdPort
) {
    fun execute(productId: Long): Result<Unit, DomainException> {
        return deleteProductByIdPort.execute(productId)
    }
}