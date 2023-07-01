package com.soat220.lanchonete.clientTotem.port

import com.soat220.lanchonete.clientTotem.model.Order
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.result.Result

interface CreateOrderPort {
    fun execute(order: Order): Result<Order, DomainException>
}