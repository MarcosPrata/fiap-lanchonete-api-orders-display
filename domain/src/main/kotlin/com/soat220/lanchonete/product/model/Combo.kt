package com.soat220.lanchonete.domain.product.model

import com.soat220.lanchonete.domain.product.model.Product

class Combo (
    val name: String,
    val price: Double,
    val products: MutableList<Product>
)