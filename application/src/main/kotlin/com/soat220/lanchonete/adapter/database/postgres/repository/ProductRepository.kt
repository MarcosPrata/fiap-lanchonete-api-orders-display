package com.soat220.lanchonete.adapter.database.postgres.repository

import com.soat220.lanchonete.adapter.database.postgres.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {
}