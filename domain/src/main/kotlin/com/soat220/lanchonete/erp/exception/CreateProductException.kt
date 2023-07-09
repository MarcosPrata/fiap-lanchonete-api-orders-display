package com.soat220.lanchonete.erp.exception

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode

class CreateProductException(
    name: String,
    errorCode: ErrorCode? = null
) : DomainException(
    message = "error creating product $name",
    errorCode = errorCode ?: ErrorCode.CREATE_PRODUCT_ERROR
) {
    constructor(
        name: String,
        details: List<DomainException>
    ) : this(name) {
        this.details = details
    }
}