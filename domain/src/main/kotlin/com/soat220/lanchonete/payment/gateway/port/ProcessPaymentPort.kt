package com.soat220.lanchonete.payment.gateway.port

import com.soat220.lanchonete.order.model.Order

interface ProcessPaymentPort {

    fun execute(order: Order, totalAmount: Double): Boolean
}