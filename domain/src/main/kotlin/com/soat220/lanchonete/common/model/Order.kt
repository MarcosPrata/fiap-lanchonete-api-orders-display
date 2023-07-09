package com.soat220.lanchonete.common.model

import com.soat220.lanchonete.common.model.enums.OrderStatus
import java.time.LocalDateTime

class Order(
    val id: Long? = null,
    var customer: Customer? = null,
    var orderItems: MutableList<OrderItem>,
    val orderStatus: OrderStatus,
    val notes: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
