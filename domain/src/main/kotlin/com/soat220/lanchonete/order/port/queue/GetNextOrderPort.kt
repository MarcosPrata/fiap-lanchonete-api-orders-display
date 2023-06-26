package com.soat220.lanchonete.order.port.queue

import com.soat220.lanchonete.order.model.Order

interface GetNextOrderPort {
    fun execute(): Order?
}