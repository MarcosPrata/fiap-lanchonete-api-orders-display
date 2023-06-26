package com.soat220.lanchonete.order.adapter.queue.inmemory

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.queue.ListAllPort
import com.soat220.lanchonete.order.port.queue.OrderQueuePort
import org.springframework.stereotype.Service

@Service
class ListAllAdapter (
    private val orderQueuePort: OrderQueuePort
): ListAllPort {
    override fun execute(): List<Order> {
        return orderQueuePort.listAll()
    }
}