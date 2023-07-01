package com.soat220.lanchonete.clientTotem.driven.postgresdb

import com.soat220.lanchonete.clientTotem.driven.postgresdb.model.Order
import com.soat220.lanchonete.erp.driven.postgresdb.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}