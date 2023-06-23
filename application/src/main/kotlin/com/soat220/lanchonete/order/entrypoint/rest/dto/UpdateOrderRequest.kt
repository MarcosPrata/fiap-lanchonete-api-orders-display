package com.soat220.lanchonete.order.entrypoint.rest.dto

data class UpdateOrderRequest  (

    internal val orderId: Long,
    internal val productsIds: List<Long>,
    internal val notes: String,
    internal val status: String

)