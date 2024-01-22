package com.soat220.lanchonete.ordersDisplay.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.driven.helper.OrderHelper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class FindAllOrdersAdapterTest {

    private val orderRepository: OrderRepository = Mockito.mock(OrderRepository::class.java)
    private val findAllOrdersAdapter = FindAllOrdersAdapter(orderRepository)

    @Test
    fun shouldFindAllOrdersSuccessful() {

        `when`(orderRepository.findAll()).thenReturn(getOrderList())

        val result = findAllOrdersAdapter.execute()

        Assertions.assertThat(result).isExactlyInstanceOf(Success::class.java)
        Assertions.assertThat(result.getOrNull())
            .usingRecursiveComparison()
            .ignoringFields("updatedAt")
            .ignoringFields("createdAt")
            .isEqualTo(getOrderList().map { it.toDomain() })
    }

    @Test
    fun shouldHandleDatabaseException() {
        `when`(orderRepository.findAll()).thenThrow(RuntimeException::class.java)

        val result = findAllOrdersAdapter.execute()

        Assertions.assertThat(result).isExactlyInstanceOf(Failure::class.java)
        Assertions.assertThat(result.getOrNull()).isNull()

    }

    private fun getOrderList(): List<Order> {

        return listOf(
            OrderHelper.createOrder(),
            OrderHelper.createOrder(id = 2),
            OrderHelper.createOrder(id = 3),
        )
    }
}