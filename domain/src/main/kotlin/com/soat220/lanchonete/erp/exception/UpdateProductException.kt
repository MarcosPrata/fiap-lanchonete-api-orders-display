package com.soat220.lanchonete.erp.exception

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode

class UpdateProductException(
    errorCode: ErrorCode? = null
) : DomainException(
    message = "error updating product",
    errorCode = errorCode ?: ErrorCode.CREATE_PRODUCT_ERROR
) {
    constructor(
        productId: Long? = null,
        details: List<DomainException>
    ) : this() {
        this.message = "error updating product with id $productId"
        this.details = details
    }
}