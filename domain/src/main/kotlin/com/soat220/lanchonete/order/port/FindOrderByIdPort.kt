package com.soat220.lanchonete.order.port

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.result.Result

interface FindOrderByIdPort {
    fun execute(orderId: Long): Result<Order, DomainException>
}