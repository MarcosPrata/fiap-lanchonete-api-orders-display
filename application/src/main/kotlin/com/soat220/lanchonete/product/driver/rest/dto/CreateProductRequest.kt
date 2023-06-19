package com.soat220.lanchonete.product.driver.rest.dto

import com.soat220.lanchonete.product.model.Category
import com.soat220.lanchonete.product.model.Product

data class CreateProductRequest(
    val name: String,
    val price: Double,
    val category: Category
) {
    fun toDomain() = Product(
        name = name,
        price = price,
        category = category,
        imageUrls = listOf()
    )
}