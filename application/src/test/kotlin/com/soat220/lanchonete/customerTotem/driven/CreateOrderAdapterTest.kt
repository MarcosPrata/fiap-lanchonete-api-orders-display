package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderItemRepository
import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.driven.helper.OrderHelper.Companion.createOrder
import com.soat220.lanchonete.customerTotem.driven.helper.OrderHelper.Companion.createOrderItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class CreateOrderAdapterTest {

    private val orderRepository = mock(OrderRepository::class.java)
    private val orderItemRepository = mock(OrderItemRepository::class.java)
    private val createOrderAdapter: CreateOrderAdapter = CreateOrderAdapter(orderRepository, orderItemRepository)

    @Test
    fun shouldCreateOrderSuccessful() {
        val order = createOrder()
        val orderItems = mutableListOf(createOrderItem())

        order.orderItems = orderItems

        doAnswer { it.arguments[0] }.`when`(orderRepository).saveAndFlush(any(Order::class.java))
        doAnswer { it.arguments[0] }.`when`(orderItemRepository).saveAllAndFlush(anyList())

        val result = createOrderAdapter.execute(order.toDomain())

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull()).usingRecursiveComparison().isEqualTo(order.toDomain())
    }

    @Test
    fun shouldHandleDatabaseException() {
        val order = createOrder()

        `when`(orderRepository.saveAndFlush(any(Order::class.java))).thenThrow(RuntimeException::class.java)

        val result = createOrderAdapter.execute(order.toDomain())

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
    }

}