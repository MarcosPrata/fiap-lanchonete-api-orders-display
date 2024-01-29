package com.soat220.lanchonete.kitchen.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.kitchen.port.FindOrdersByStatusPort
import javax.inject.Named

@Named
open class FindReceivedOrders(
    private val findOrdersByStatusPort: FindOrdersByStatusPort
) {
    open fun execute(): Result<List<Order>, DomainException> {
        return findOrdersByStatusPort.execute(OrderStatus.RECEIVED)
    }
}