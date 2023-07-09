package com.soat220.lanchonete.common.driven.postgresdb

import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findAllByStatusOrderByCreatedAtAsc(orderStatus: OrderStatus): List<Order>
}