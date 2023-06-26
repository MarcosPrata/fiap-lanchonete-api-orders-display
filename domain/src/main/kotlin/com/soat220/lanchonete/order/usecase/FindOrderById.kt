package com.soat220.lanchonete.order.usecase

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.FindOrderByIdPort
import com.soat220.lanchonete.result.Result
import javax.inject.Named

@Named
class FindOrderById(
    private val findOrderByIdPort: FindOrderByIdPort
) {
    fun execute(orderId: Long): Result<Order, DomainException> {
        return findOrderByIdPort.execute(orderId)
    }
}