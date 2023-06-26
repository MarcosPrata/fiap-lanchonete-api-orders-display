package com.soat220.lanchonete.order.entrypoint.rest.dto

class CreateOrderRequest (

    internal val productsIds: List<Long>,
    internal val notes: String,
    internal val customerCpf: String?

)