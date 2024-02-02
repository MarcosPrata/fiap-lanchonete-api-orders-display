package com.soat220.lanchonete.ordersDisplay.driven.helper

import com.soat220.lanchonete.common.driven.postgresdb.model.Customer
import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus

class OrderHelper {

    companion object {

        fun createOrder(
            id: Long? = 1,
            customer: Customer? = CustomerHelper.createCustomer(),
            orderStatus: OrderStatus? = OrderStatus.RECEIVED,
            notes: String? = "sem cebola"): Order {
            return Order(id = id, customer = customer, status = orderStatus!!, notes = notes!!)
        }

    }
}