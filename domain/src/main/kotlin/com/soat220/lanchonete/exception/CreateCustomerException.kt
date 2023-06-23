package com.soat220.lanchonete.exception

class CreateClientException(
    message: String
) : DomainException(
    message = message,
    errorCode = ErrorCode.CREATE_CLIENT_ERROR
) {
    constructor(
        name: String,
        details: List<DomainException>
    ) : this(name) {
        this.details = details
    }
}