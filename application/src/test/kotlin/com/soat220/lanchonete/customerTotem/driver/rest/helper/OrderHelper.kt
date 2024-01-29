package com.soat220.lanchonete.customerTotem.driver.rest.helper

import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.customerTotem.driver.rest.dto.request.CreateCustomerRequest
import com.soat220.lanchonete.customerTotem.driver.rest.dto.request.CreateOrderItemRequest
import com.soat220.lanchonete.customerTotem.driver.rest.dto.request.CreateOrderRequest
import com.soat220.lanchonete.customerTotem.driver.rest.helper.CustomerHelper.Companion.createCustomer
import com.soat220.lanchonete.customerTotem.driver.rest.helper.CustomerHelper.Companion.createCustomerRequest
import java.time.LocalDateTime

class OrderHelper {

    companion object {

        fun createOrder(
            id: Long? = 1,
            customer: Customer? = createCustomer(),
            orderStatus: OrderStatus? = OrderStatus.RECEIVED,
            notes: String? = "Sem cebola",
            createdAt: LocalDateTime = LocalDateTime.now(),
            updatedAt: LocalDateTime = LocalDateTime.now(),
        ) = Order(id = id, customer = customer, orderStatus = orderStatus!!, notes = notes!!, createdAt = createdAt, updatedAt = updatedAt, orderItems = mutableListOf())

        fun createOrderRequest(
            customer: CreateCustomerRequest? = createCustomerRequest(),
            notes: String? = "Sem cebola",
            orderItems: List<CreateOrderItemRequest>? = listOf(createOrderItemRequest())
        ) = CreateOrderRequest(customer = customer, notes = notes, orderItems = orderItems!!)

        fun createOrderItemRequest(
            productId: Long? = 1,
            amount: Long = 1
        ) = CreateOrderItemRequest(productId = productId!!, amount = amount)

    }
}