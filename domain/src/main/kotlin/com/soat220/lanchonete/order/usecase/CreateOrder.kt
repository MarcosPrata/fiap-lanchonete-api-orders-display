package com.soat220.lanchonete.order.usecase

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.CreateOrderPort
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result
import javax.inject.Named

@Named
class CreateOrder(
    private val createOrderPort: CreateOrderPort
) {

    fun execute(customerCpf: String?, productsIds: List<Long>, notes: String): Result<Order, DomainException> {
        return createOrderPort.execute(customerCpf, productsIds, notes)
    }

}