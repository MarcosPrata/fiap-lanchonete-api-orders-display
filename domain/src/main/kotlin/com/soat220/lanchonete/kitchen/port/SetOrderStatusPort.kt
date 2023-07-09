package com.soat220.lanchonete.kitchen.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Result

interface SetOrderStatusPort {
    fun execute(orderId: Long, orderStatus: OrderStatus): Result<Order, DomainException>
}