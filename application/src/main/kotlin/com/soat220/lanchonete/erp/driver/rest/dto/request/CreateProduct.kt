package com.soat220.lanchonete.erp.driver.rest.dto.request

import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.model.enums.Category

data class CreateProduct(
    val name: String,
    val description: String,
    val price: Double,
    val category: Category,
    val imageUrls: List<String>
) {
    fun toDomain() = Product(
        name = name,
        description = description,
        category = category,
        price = price,
        imageUrls = imageUrls,
        deleted = false
    )
}