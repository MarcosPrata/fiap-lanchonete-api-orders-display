package com.soat220.lanchonete.erp.exception

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode

class DeleteProductByIdException(
    errorCode: ErrorCode? = null
) : DomainException(
    message = "error deleting product",
    errorCode = errorCode ?: ErrorCode.CREATE_PRODUCT_ERROR
) {
    constructor(
        productId: Long? = null,
        details: List<DomainException>
    ) : this() {
        this.message = "error deleting product with id $productId"
        this.details = details
    }
}