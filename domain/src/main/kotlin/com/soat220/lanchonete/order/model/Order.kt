package com.soat220.lanchonete.order.model

import com.soat220.lanchonete.order.model.enums.OrderStatus
import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.product.model.Product
import java.time.LocalDateTime

class Order(

    val id: Long?,
    var customer: Customer?,
    val products: MutableList<Product>?,
    val orderStatus: OrderStatus?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val notes: String

)
