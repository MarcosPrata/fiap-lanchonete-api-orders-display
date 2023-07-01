package com.soat220.lanchonete.clientTotem.driver.rest.dto.request

import com.soat220.lanchonete.clientTotem.model.Order

data class CreateOrder(
    val clientOrder: ClientOrder,
    val orderItems: MutableList<OrderItem>
) {
    fun toDomain() = Order(
        client = clientOrder.toDomain(),
        orderItens = orderItems.map { it.toDomain() }.toMutableList()
    )
}