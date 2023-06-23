package com.soat220.lanchonete.order.adapter.postgresdb

import com.soat220.lanchonete.order.adapter.postgresdb.model.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Long> {
    fun findAllByOrderByCreatedAtAsc(): List<OrderEntity>
}