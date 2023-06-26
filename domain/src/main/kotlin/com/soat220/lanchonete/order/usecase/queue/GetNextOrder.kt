package com.soat220.lanchonete.order.usecase.queue

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.queue.GetNextOrderPort
import javax.inject.Named

@Named
class GetNextOrder(
    private val getNextOrderPort: GetNextOrderPort
) {
    fun execute(): Order? {
        return getNextOrderPort.execute()
    }
}