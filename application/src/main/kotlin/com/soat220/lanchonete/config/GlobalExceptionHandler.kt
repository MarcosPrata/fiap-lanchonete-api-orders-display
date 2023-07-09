package com.soat220.lanchonete.config

import com.soat220.lanchonete.common.exception.AlreadyExistsException
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(value = [(Exception::class), (RuntimeException::class)])
    fun exception(ex: Exception): ResponseEntity<ErrorDetails> {
        log.error("Exception", ex)

        return if (ex is DomainException) {
            return when (ex) {
                is AlreadyExistsException -> handleException(ex, HttpStatus.CONFLICT)
                is NotFoundException -> handleException(ex, HttpStatus.NOT_FOUND)
                else -> handleException(ex, HttpStatus.BAD_REQUEST)
            }
        } else {
            handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    private fun handleException(
        ex: Exception,
        status: HttpStatus
    ): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(ex, status)

        return ResponseEntity(errorDetails, status)
    }
}


