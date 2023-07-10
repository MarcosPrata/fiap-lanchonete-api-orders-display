package com.soat220.lanchonete.common.model

import com.soat220.lanchonete.common.model.enums.Category

class Product(
    val id: Long? = null,
    val name: String,
    val description: String,
    val category: Category,
    val price: Double,
    val imageUrls: List<String>,
    val deleted: Boolean
)