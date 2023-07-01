package com.soat220.lanchonete.clientDisplay.driver.rest.dto

import com.soat220.lanchonete.common.model.Category
import com.soat220.lanchonete.common.model.Product

data class CreateProductRequest(
    val name: String,
    val price: Double,
    val category: Category
) {
    fun toDomain() = Product(
        name = name,
        price = price,
        category = category,
    )
}