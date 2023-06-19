package com.soat220.lanchonete.adapter.entrypoint.rest.product.dto

import com.soat220.lanchonete.domain.model.Product

data class CreateProductRequest(
    val name: String,
    val price: Double
) {
    fun toDomain() = Product(
        name,
        price
    )
}
