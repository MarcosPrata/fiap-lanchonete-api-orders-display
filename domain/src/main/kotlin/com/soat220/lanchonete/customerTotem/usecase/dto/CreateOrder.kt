package com.soat220.lanchonete.customerTotem.usecase.dto

class CreateOrder(
    val customer: Customer?,
    val orderItems: List<OrderItem>,
    val notes: String?
)