package com.soat220.lanchonete.adapter.entrypoint.rest.product.dto

import com.soat220.lanchonete.product.model.Product

data class CreateProductRequest(
    val name: String,
    val price: Double
) {
    fun toDomain() = Product(
        name,
        price
    )
}
