package com.soat220.lanchonete.order.adapter.queue.inmemory

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.queue.OrderQueuePort
import org.springframework.stereotype.Service
import java.util.*

@Service
class InMemoryQueueAdapter(): OrderQueuePort {

    private val orderList: LinkedList<Order> = LinkedList()

    override fun addToQueue(order: Order) {
        orderList.add(order)
    }

    override fun getNextOrder(): Order? {
        return orderList.pollFirst()
    }

    override fun clearQueue() {
        orderList.clear()
    }

    override fun listAll(): List<Order> {
        return orderList.toList()
    }
}