package com.soat220.lanchonete.common.driven.postgresdb

import com.soat220.lanchonete.common.driven.postgresdb.model.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository : JpaRepository<OrderItem, Long>