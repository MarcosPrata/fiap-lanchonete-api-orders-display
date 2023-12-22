package com.soat220.lanchonete.helper

import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.model.enums.Category

class ProductSupport {

    companion object {

        fun createDefaultProduct(): Product {
            return Product(1L, "bread", "hamburguer bread", Category.SANDWICH, 10.0, emptyList(), false)
        }

    }
}