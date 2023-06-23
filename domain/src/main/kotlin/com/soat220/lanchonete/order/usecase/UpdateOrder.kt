package com.soat220.lanchonete.order.usecase

import com.soat220.lanchonete.order.port.UpdateOrderPort
import javax.inject.Named

@Named
class UpdateOrder(
    private val updateOrderPort: UpdateOrderPort
) {

    fun execute(orderId: Long, productsIds: List<Long>, orderStatus: String, notes: String) {
        updateOrderPort.execute(orderId, productsIds, orderStatus, notes)
    }

}