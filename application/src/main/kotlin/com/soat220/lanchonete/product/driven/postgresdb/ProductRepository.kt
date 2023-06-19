package com.soat220.lanchonete.product.driven.postgresdb

import com.soat220.lanchonete.product.driven.postgresdb.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}