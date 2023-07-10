package com.soat220.lanchonete.ordersDisplay.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.ordersDisplay.port.FindAllOrdersPort
import javax.inject.Named

@Named
class FindAllOrders(
    private val findAllOrdersPort: FindAllOrdersPort
) {

    fun execute(): Result<List<Order>, DomainException> {
        val orders = findAllOrdersPort.execute()
            .orThrow()
            .filter { it.orderStatus != OrderStatus.COMPLETED }

        return Success(orders)
    }
}