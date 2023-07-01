package com.soat220.lanchonete.common.exception


open class DomainException(
    override var message: String?,
    open var errorCode: ErrorCode,
) : RuntimeException() {
    open var details: List<DomainException> = listOf()

    constructor(ex: RuntimeException) : this(
        message = ex.message ?: ex.localizedMessage,
        ErrorCode.GENERIC_ERROR
    )

    constructor(ex: Exception) : this(
        message = ex.message ?: ex.localizedMessage,
        ErrorCode.GENERIC_ERROR
    )

    constructor(ex: Exception, errorCode: ErrorCode) : this(
        message = ex.message ?: ex.localizedMessage,
        errorCode
    )

    constructor(ex: Throwable) : this(
        message = ex.message ?: ex.localizedMessage,
        ErrorCode.GENERIC_ERROR
    )
}