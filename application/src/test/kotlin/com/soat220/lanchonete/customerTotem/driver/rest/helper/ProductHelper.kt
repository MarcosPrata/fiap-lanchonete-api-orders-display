package com.soat220.lanchonete.customerTotem.driver.rest.helper

import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.model.enums.Category

class ProductHelper {

    companion object {

        fun createProduct(
            id: Long? = 1,
            name: String? = "Coca cola",
            description: String? = "Refrigerante de cola",
            category: Category? = Category.BEVERAGE,
            deleted: Boolean? = false,
            price: Double? = 5.0
        ) = Product(id = id, name = name!!, description = description!!, category = category!!, deleted = deleted!!, price = price!!, imageUrls = listOf())


    }
}