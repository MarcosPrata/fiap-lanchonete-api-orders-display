package com.soat220.lanchonete.erp.driven.helper

import com.soat220.lanchonete.common.driven.postgresdb.model.Product
import com.soat220.lanchonete.common.driven.postgresdb.model.ProductImage
import com.soat220.lanchonete.common.model.enums.Category

class ProductHelper {

    companion object {

        fun createProduct(
            id: Long? = 1,
            name: String? = "Coca-cola",
            description: String? = "refrigerante de cola",
            category: Category? = Category.BEVERAGE,
            price: Double? = 5.0,
            images: MutableList<ProductImage>? = mutableListOf(),
            deleted: Boolean? = false
        ) = Product(id = id, name = name!!, description = description!!, category = category!!, price = price!!, images = images!!, deleted = deleted!!)

    }
}