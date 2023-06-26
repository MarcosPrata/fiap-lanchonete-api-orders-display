package com.soat220.lanchonete.payment.usecase

import com.soat220.lanchonete.payment.port.HandlePaymentPort
import javax.inject.Named

@Named
class HandlePayment(
    private val handlePaymentPort: HandlePaymentPort
) {
    fun execute(orderId: Long, totalAmount: Double) {
        handlePaymentPort.execute(orderId, totalAmount)
    }

}