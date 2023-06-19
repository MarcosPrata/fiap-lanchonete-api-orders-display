package com.soat220.lanchonete.domain.port.driven

import com.soat220.lanchonete.domain.model.Product

interface ProductPersistenceInterface {

    fun save(product: Product): Product
}