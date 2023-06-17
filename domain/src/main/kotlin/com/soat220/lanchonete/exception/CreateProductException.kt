package com.soat220.lanchonete.exception

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