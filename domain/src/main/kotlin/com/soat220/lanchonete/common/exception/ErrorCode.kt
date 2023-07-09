package com.soat220.lanchonete.common.exception

enum class ErrorCode(val code: Int) {
    GENERIC_ERROR(1),
    ENTITY_NOT_FOUND_ERROR(2),
    ENTITY_ALREADY_EXISTS_ERROR(3),
    DATABASE_ERROR(4),
    CREATE_PRODUCT_ERROR(5),
    CREATE_CUSTOMER_ERROR(6),
    CREATE_ORDER_ERROR(7),
    PAYMENT_NOT_APPROVED(8)
}
