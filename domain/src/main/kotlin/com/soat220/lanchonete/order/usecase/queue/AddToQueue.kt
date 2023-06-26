package com.soat220.lanchonete.order.usecase.queue

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.queue.AddToQueuePort
import javax.inject.Named

@Named
class AddToQueue(
    private val addToQueuePort: AddToQueuePort
) {
    fun execute(order: Order) {
        addToQueuePort.execute(order)
    }
}