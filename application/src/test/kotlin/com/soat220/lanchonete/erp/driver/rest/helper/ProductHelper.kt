package com.soat220.lanchonete.erp.driver.rest.helper

import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.model.enums.Category
import com.soat220.lanchonete.erp.driver.rest.dto.request.CreateProduct
import com.soat220.lanchonete.erp.driver.rest.dto.request.UpdateProduct

class ProductHelper {

    companion object {

        fun createProductRequest(
            name: String? = "Coca-cola",
            description: String? = "refrigerante de cola",
            category: Category? = Category.BEVERAGE,
            price: Double? = 5.0,
            images: List<String>? = listOf()
        ) = CreateProduct(name = name!!, description = description!!, category = category!!, price = price!!, imageUrls = images!!)

        fun createProductModel(
            id: Long? = 1,
            name: String? = "Coca-cola",
            description: String? = "refrigerante de cola",
            category: Category? = Category.BEVERAGE,
            price: Double? = 5.0,
            images: List<String>? = listOf(),
            deleted: Boolean? = false
        ) = Product(id = id, name = name!!, description = description!!, category = category!!, price = price!!, imageUrls = images!!, deleted = deleted!!)

        fun updateProductRequest (
            name: String? = "Coca-cola",
            description: String? = "refrigerante de cola",
            category: Category? = Category.BEVERAGE,
            price: Double? = 5.0,
            images: List<String>? = listOf(),
            deleted: Boolean? = false
        ) = UpdateProduct(name = name!!, description = description!!, category = category!!, price = price!!, imageUrls = images!!, deleted = deleted!!)
    }
}