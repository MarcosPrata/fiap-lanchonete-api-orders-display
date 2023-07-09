package com.soat220.lanchonete.erp.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result

interface DeleteProductByIdPort {
    fun execute(productId: Long): Result<Unit, DomainException>
}