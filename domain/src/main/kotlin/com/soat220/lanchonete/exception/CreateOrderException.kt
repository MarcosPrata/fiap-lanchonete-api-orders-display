package com.soat220.lanchonete.exception

import com.soat220.lanchonete.order.model.Order

class CreateOrderException(
    message: String
) : DomainException(
    message = message,
    errorCode = ErrorCode.CREATE_ORDER_ERROR
) {
    constructor(
        order: Order?,
        details: List<DomainException>
    ) : this(order.toString()) {
        this.details = details
    }
}