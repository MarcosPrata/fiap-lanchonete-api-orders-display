package com.soat220.lanchonete.clientTotem.driver.rest.dto.request

import com.soat220.lanchonete.clientTotem.model.OrderItem

data class OrderItem(
    val productId: Long,
    val ammount: Int,
    val note: String
) {
    fun toDomain() = OrderItem(
        productId = productId,
        ammount = ammount,
        note = note
    )
}