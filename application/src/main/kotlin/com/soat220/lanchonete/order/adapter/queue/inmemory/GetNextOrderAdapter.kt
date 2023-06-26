package com.soat220.lanchonete.order.adapter.queue.inmemory

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.queue.GetNextOrderPort
import com.soat220.lanchonete.order.port.queue.OrderQueuePort
import org.springframework.stereotype.Service

@Service
class GetNextOrderAdapter(
    private val orderQueuePort: OrderQueuePort
): GetNextOrderPort {

    override fun execute(): Order? {
        return orderQueuePort.getNextOrder()
    }
}