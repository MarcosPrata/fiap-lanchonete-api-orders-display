package com.soat220.lanchonete.kitchen.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.driven.helper.OrderHelper.Companion.createOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class FindOrdersByStatusAdapterTest {

    private val orderRepository: OrderRepository = mock(OrderRepository::class.java)
    private val findOrdersByStatusAdapter = FindOrdersByStatusAdapter(orderRepository)

    @BeforeEach
    fun setup() {
        `when`(orderRepository.findAllByStatusOrderByCreatedAtAsc(OrderStatus.RECEIVED))
            .thenReturn(getOrderList(OrderStatus.RECEIVED))
    }

    @Test
    fun shouldFindOrderByStatusSuccessful() {

        val result = findOrdersByStatusAdapter.execute(OrderStatus.RECEIVED)

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull())
            .usingRecursiveComparison()
            .ignoringFields("updatedAt")
            .ignoringFields("createdAt")
            .isEqualTo(getOrderList(OrderStatus.RECEIVED).map { it.toDomain() })

    }

    @Test
    fun shouldNotFindOrderByStatus() {
        val result = findOrdersByStatusAdapter.execute(OrderStatus.IN_PREPARATION)

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull()).asList().isEmpty()
    }

    @Test
    fun shouldHandleDatabaseException() {
        `when`(orderRepository.findAllByStatusOrderByCreatedAtAsc(OrderStatus.COMPLETED))
            .thenThrow(RuntimeException::class.java)

        val result = findOrdersByStatusAdapter.execute(OrderStatus.COMPLETED)

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
        assertThat(result.getOrNull()).isNull()

    }

    private fun getOrderList(orderStatus: OrderStatus): List<Order> {

        return listOf(
            createOrder(orderStatus = orderStatus),
            createOrder(id = 2, orderStatus = orderStatus),
            createOrder(id = 3, orderStatus = orderStatus),
        )
    }


}