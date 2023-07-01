package com.soat220.lanchonete.erp.exception

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode

class FindProductByIdException(
    errorCode: ErrorCode? = null
) : DomainException(
    message = "error searching product by id",
    errorCode = errorCode ?: ErrorCode.CREATE_PRODUCT_ERROR
) {
    constructor(
        productId: Long? = null,
        details: List<DomainException>
    ) : this() {
        this.message = "error searching product by id $productId"
        this.details = details
    }
}