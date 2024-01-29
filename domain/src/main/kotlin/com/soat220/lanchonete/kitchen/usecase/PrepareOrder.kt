package com.soat220.lanchonete.kitchen.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.kitchen.port.FindOrdersByStatusPort
import com.soat220.lanchonete.kitchen.port.SetOrderStatusPort
import javax.inject.Named

@Named
open class PrepareOrder(
    private val setOrderStatusPort: SetOrderStatusPort
) {
    open fun execute(orderId: Long): Result<Order, DomainException> {
        return setOrderStatusPort.execute(orderId, OrderStatus.IN_PREPARATION)
    }
}