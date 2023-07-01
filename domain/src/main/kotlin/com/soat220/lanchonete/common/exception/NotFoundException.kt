package com.soat220.lanchonete.common.exception

class NotFoundException(
    entityClass: Class<*>,
    errorCode: ErrorCode? = null
) : DomainException(
    message = "${entityClass.simpleName} not found",
    errorCode = errorCode ?: ErrorCode.ENTITY_NOT_FOUND_ERROR
)