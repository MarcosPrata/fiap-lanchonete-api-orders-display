package com.soat220.lanchonete.exception

class PaymentNotApprovedException(message: String, errorCode: ErrorCode) :
    DomainException(
        message = message,
        errorCode = errorCode
    )