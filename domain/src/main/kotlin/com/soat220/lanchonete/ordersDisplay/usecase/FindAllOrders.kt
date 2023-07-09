package com.soat220.lanchonete.ordersDisplay.usecase

import com.soat220.lanchonete.ordersDisplay.port.FindAllOrdersPort
import javax.inject.Named

@Named
class FindAllOrders(
    private val findAllOrdersPort: FindAllOrdersPort
) {

    fun execute() = findAllOrdersPort.execute()
}