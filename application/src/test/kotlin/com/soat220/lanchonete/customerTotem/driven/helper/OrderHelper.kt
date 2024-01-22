package com.soat220.lanchonete.customerTotem.driven.helper

import com.soat220.lanchonete.common.driven.postgresdb.model.Customer
import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.common.driven.postgresdb.model.OrderItem
import com.soat220.lanchonete.common.driven.postgresdb.model.Product
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.customerTotem.driven.helper.CustomerHelper.Companion.createCustomer
import com.soat220.lanchonete.customerTotem.driven.helper.ProductHelper.Companion.createProduct

class OrderHelper {

    companion object {

        fun createOrder(
                id: Long? = 1,
                customer: Customer? = createCustomer(),
                orderStatus: OrderStatus? = OrderStatus.RECEIVED,
                notes: String? = "sem cebola"): Order {
            return Order(id = id, customer = customer, status = orderStatus!!, notes = notes!!)
        }

        fun createOrderItem(
                id: Long? = 1,
                order: Order? = createOrder(),
                product: Product? = createProduct(),
                amount: Int? = 1
        ) = OrderItem(id = id, order = order, product = product!!, amount = amount!!)

    }
}