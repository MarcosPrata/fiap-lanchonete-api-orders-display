package com.soat220.lanchonete.clientTotem.model

class Order(
    val id: Long? = null,
    val client: Client,
    val orderItens: MutableList<OrderItem>
)