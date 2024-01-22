package com.soat220.lanchonete.kitchen.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.kitchen.driven.helper.OrderHelper.Companion.createOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.*

class SetOrderStatusAdapterTest {

    private val orderRepository: OrderRepository = Mockito.mock(OrderRepository::class.java)
    private val setOrderStatusAdapter = SetOrderStatusAdapter(orderRepository)

    @Test
    fun shouldSetOrderStatusSuccessful() {

        `when`(orderRepository.findById(1))
            .thenReturn(Optional.of(createOrder(orderStatus = OrderStatus.IN_PREPARATION)))

        doAnswer { it.arguments[0] }.`when`(orderRepository).save(any())

        val result = setOrderStatusAdapter.execute(1, OrderStatus.COMPLETED)

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull())
            .extracting("orderStatus")
            .isEqualTo(OrderStatus.COMPLETED)
    }

    @Test
    fun shouldHandleOrderNotFound() {
        `when`(orderRepository.findById(1)).thenReturn(Optional.empty())

        val result = setOrderStatusAdapter.execute(1, OrderStatus.COMPLETED)

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
        verify(orderRepository, never()).save(any())
    }
}