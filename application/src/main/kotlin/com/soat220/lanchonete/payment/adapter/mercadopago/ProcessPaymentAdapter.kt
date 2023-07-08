package com.soat220.lanchonete.payment.adapter.mercadopago

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.payment.gateway.port.ProcessPaymentPort
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ProcessPaymentAdapter(): ProcessPaymentPort {

    override fun execute(order: Order, totalAmount: Double): Boolean {
        val value = Random.nextInt(0, 100);
        return value <= 80;
    }
}