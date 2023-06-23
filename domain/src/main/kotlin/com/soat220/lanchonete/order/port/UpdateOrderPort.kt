package com.soat220.lanchonete.order.port

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface UpdateOrderPort {

    fun execute(orderId: Long, productsIds: List<Long>, orderStatus: String, notes: String): Result<Order, DomainException>
}