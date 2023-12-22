package com.soat220.lanchonete.helper

import com.soat220.lanchonete.customerTotem.usecase.dto.OrderItem

class OrderItemSupport {

    companion object {

        fun createOrderItem(productId: Long, amount: Long): OrderItem {
            return OrderItem(productId, amount)
        }

        fun createList(vararg orderItems: OrderItem): List<OrderItem> {
            return orderItems.asList()
        }

    }
}