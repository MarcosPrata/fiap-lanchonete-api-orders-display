package com.soat220.lanchonete.order.adapter.queue.inmemory

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.queue.AddToQueuePort
import com.soat220.lanchonete.order.port.queue.OrderQueuePort
import org.springframework.stereotype.Service

@Service
class AddToQueueAdapter(
    private val orderQueuePort: OrderQueuePort
) : AddToQueuePort {

    override fun execute(order: Order) {
        orderQueuePort.addToQueue(order)
    }
}