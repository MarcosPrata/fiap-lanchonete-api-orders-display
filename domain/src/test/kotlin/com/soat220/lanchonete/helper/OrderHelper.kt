package com.soat220.lanchonete.helper

import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.OrderItem
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.helper.CustomerHelper.Companion.createCustomer
import com.soat220.lanchonete.helper.ProductHelper.Companion.createProduct
import java.time.LocalDateTime
import com.soat220.lanchonete.customerTotem.usecase.dto.OrderItem as OrderItemDto

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

        fun createOrderItem(
            id: Long? = 1,
            product: Product? = createProduct(),
            amount: Int? = 1
        ) = OrderItem(id = id, product = product!!, amount = amount!!)

        fun createOrderItemDto(
            productId: Long? = 1,
            amount: Long? = 1
        ) = OrderItemDto(productId = productId!!, amount = amount!!)
    }
}