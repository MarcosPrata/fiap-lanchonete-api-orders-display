package com.soat220.lanchonete.order.port.queue

import com.soat220.lanchonete.order.model.Order

interface OrderQueuePort {

    fun addToQueue(order: Order)

    fun getNextOrder(): Order?

    fun clearQueue()

    fun listAll(): List<Order>
}