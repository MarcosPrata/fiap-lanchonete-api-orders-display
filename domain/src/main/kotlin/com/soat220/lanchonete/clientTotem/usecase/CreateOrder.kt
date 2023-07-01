package com.soat220.lanchonete.clientTotem.usecase

import com.soat220.lanchonete.clientTotem.model.Order
import com.soat220.lanchonete.clientTotem.port.CreateOrderPort
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result
import javax.inject.Named

@Named
class CreateOrder(
    private val createOrderPort: CreateOrderPort
) {
    fun execute(order: Order): Result<Order, DomainException> {
        return createOrderPort.execute(order)
    }
}