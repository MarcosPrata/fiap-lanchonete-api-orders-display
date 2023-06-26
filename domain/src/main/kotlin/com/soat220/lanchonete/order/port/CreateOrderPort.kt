package com.soat220.lanchonete.order.port

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface CreateOrderPort {

    fun execute(customerCpf: String?, productsIds: List<Long>, notes: String): Result<Order, DomainException>
}