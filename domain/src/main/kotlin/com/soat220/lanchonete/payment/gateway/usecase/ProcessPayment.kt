package com.soat220.lanchonete.payment.gateway.usecase

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.payment.gateway.port.ProcessPaymentPort
import javax.inject.Named

@Named
class ProcessPayment(
    private val processPaymentPort: ProcessPaymentPort
) {
    fun execute(order: Order, totalAmount: Double): Boolean {
        return processPaymentPort.execute(order, totalAmount)
    }
}