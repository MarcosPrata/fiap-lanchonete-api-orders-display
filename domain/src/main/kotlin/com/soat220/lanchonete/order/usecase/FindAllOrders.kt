package com.soat220.lanchonete.order.usecase

import com.soat220.lanchonete.order.port.FindAllOrdersPort
import javax.inject.Named

@Named
class FindAllOrders(
    private val findAllOrdersPort: FindAllOrdersPort
) {

    fun execute() = findAllOrdersPort.execute()
}