package com.soat220.lanchonete.product.adapter.postgresdb

import com.soat220.lanchonete.product.adapter.postgresdb.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {
}