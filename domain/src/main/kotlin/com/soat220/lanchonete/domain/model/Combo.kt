package com.soat220.lanchonete.domain.model

class Combo (
    val name: String,
    val price: Double,
    val products: MutableList<Product>
)