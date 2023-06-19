package com.soat220.lanchonete.product.model

class Product (
    val id: Long? = null,
    val name: String,
    val price: Double,
    val category: Category,
    val imageUrls: List<String>
)