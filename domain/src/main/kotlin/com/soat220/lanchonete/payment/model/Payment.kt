package com.soat220.lanchonete.payment.model

import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.payment.model.enums.PaymentStatus
import java.time.LocalDateTime

class Payment(

    val id: Long?,
    val order: Order,
    val paymentStatus: PaymentStatus,
    val createdAt: LocalDateTime,
    val totalAmount: Double

) {}