package com.soat220.lanchonete.exception

class CreateCustomerException(
    message: String
) : DomainException(
    message = message,
    errorCode = ErrorCode.CREATE_CUSTOMER_ERROR
) {
    constructor(
        name: String?,
        details: List<DomainException>
    ) : this(name?: "") {
        this.details = details
    }
}