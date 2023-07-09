package com.soat220.lanchonete.config

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import java.time.LocalDateTime
import org.springframework.http.HttpStatus

class ErrorDetails() {
    var exception: String? = null
    var message: String? = null
    var errorName: String? = ErrorCode.GENERIC_ERROR.name
    var errorCode: Int? = ErrorCode.GENERIC_ERROR.code
    val timestamp: LocalDateTime = LocalDateTime.now()
    var stacktrace: String? = null
    var details: List<ErrorDetails>? = listOf()

    constructor(ex: Exception, status: HttpStatus) : this() {
        this.exception = ex.javaClass.simpleName
        this.message = ex.message
        this.stacktrace = ex.stackTraceToString()
        if (ex is DomainException) {
            this.stacktrace = null
            this.errorName = ex.errorCode.name
            this.errorCode = ex.errorCode.code
            this.details = ex.details.map {
                ErrorDetails(it, status)
            }
        }
    }
}