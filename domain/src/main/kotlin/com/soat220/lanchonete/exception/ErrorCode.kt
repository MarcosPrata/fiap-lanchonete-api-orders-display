package com.soat220.lanchonete.exception

enum class ErrorCode(val code: Int) {
    GENERIC_ERROR(1),
    ENTITY_NOT_FOUND_ERROR(2),
    ENTITY_ALREADY_EXISTS_ERROR(3),
    DATABASE_ERROR(4),
    CREATE_PRODUCT_ERROR(5),
    CREATE_CLIENT_ERROR(6)
}
