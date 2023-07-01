package com.soat220.lanchonete.common.exception

class AlreadyExistsException(
    entityClass: Class<*>,
    errorCode: ErrorCode? = null
) : DomainException(
    message = "${entityClass.simpleName} already exists",
    errorCode = errorCode ?: ErrorCode.ENTITY_ALREADY_EXISTS_ERROR
)