package com.soat220.lanchonete.order.usecase.queue

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.queue.ListAllPort
import javax.inject.Named

@Named
class ListAll(
    private val listAllPort: ListAllPort
) {
    fun execute(): List<Order> {
        return listAllPort.execute()
    }
}