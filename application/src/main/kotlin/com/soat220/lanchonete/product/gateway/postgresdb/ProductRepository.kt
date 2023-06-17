package com.soat220.lanchonete.product.gateway.postgresdb

import com.soat220.lanchonete.product.gateway.postgresdb.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}