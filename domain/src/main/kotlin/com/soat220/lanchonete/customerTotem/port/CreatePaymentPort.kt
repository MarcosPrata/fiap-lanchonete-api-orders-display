package com.soat220.lanchonete.customerTotem.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.customerTotem.model.Payment
import com.soat220.lanchonete.customerTotem.model.PaymentStatus

interface CreatePaymentPort {
    fun execute(order: Order, paymentStatus: PaymentStatus, totalAmount: Double): Result<Payment, DomainException>
}