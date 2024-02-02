package com.soat220.lanchonete.ordersDisplay.usecase

import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.helper.OrderHelper.Companion.createOrder
import com.soat220.lanchonete.ordersDisplay.port.FindAllOrdersPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FindAllOrdersTest {

    private val findAllOrdersPort = Mockito.mock(FindAllOrdersPort::class.java)
    private val findAllOrders = FindAllOrders(findAllOrdersPort)

    @Test
    fun shouldFindOnlyOrdersWithStatusReceived() {

        Mockito.`when`(findAllOrdersPort.execute())
            .thenReturn(Success(getOrders()))

        val result = findAllOrders.execute()

        Assertions.assertTrue(result.getOrNull()!!.size == 2)
    }

    private fun getOrders(): List<Order> {
        return listOf(
            createOrder(id = 1, orderStatus = OrderStatus.RECEIVED),
            createOrder(id = 2, orderStatus = OrderStatus.RECEIVED),
            createOrder(id = 3, orderStatus = OrderStatus.COMPLETED)
        )
    }
}