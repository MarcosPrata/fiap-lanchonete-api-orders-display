package com.soat220.lanchonete.clientTotem.model

class OrderItem(
    val id: Long? = null,
    val productId: Long,
    val ammount: Int = 1,
    val note: String,
)