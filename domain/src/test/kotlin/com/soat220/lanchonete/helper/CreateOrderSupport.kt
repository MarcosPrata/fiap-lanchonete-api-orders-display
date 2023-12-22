package com.soat220.lanchonete.helper

import com.soat220.lanchonete.customerTotem.usecase.dto.CreateOrder
import com.soat220.lanchonete.customerTotem.usecase.dto.Customer
import com.soat220.lanchonete.customerTotem.usecase.dto.OrderItem

class CreateOrderSupport {

    companion object {

        fun createOrder(customer: Customer?, orderItems: List<OrderItem>, notes: String): CreateOrder {
            return CreateOrder(customer, orderItems, notes)
        }

    }
}