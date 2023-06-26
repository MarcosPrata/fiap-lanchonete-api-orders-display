package com.soat220.lanchonete.payment.port

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.enums.PaymentStatus
import com.soat220.lanchonete.result.Result

interface CreatePaymentPort {
    fun execute(order: Order, paymentStatus: PaymentStatus, totalAmount: Double): Result<Payment, DomainException>
}