package com.soat220.lanchonete.erp.driver.rest.dto.request

import com.soat220.lanchonete.common.model.Category
import com.soat220.lanchonete.common.model.Product

data class CreateProduct(
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