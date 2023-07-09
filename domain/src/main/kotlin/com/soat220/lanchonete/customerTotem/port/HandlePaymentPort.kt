package com.soat220.lanchonete.customerTotem.port

import com.soat220.lanchonete.customerTotem.model.Payment

interface HandlePaymentPort {
    fun execute(orderId: Long, totalAmount: Double): Payment
}