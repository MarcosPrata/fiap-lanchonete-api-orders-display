package com.soat220.lanchonete.kitchen.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Result

interface UpdateOrderPort {

    fun execute(
        orderId: Long,
        productsIds: List<Long>,
        orderStatus: String,
        notes: String
    ): Result<Order, DomainException>
}