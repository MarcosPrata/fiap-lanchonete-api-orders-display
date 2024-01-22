package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.driven.helper.OrderHelper.Companion.createOrder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.*

class FindOrderByIdAdapterTest {

    private val orderRepository: OrderRepository = mock(OrderRepository::class.java)
    private val findOrderByIdAdapter: FindOrderByIdAdapter = FindOrderByIdAdapter(orderRepository)

    @Test
    fun shouldFindOrderByIdSuccessful() {

        `when`(orderRepository.findById(1)).thenReturn(Optional.of(createOrder()))

        val result = findOrderByIdAdapter.execute(1)

        Assertions.assertThat(result).isExactlyInstanceOf(Success::class.java)
        Assertions.assertThat(result.getOrNull())
            .usingRecursiveComparison()
            .ignoringFields("updatedAt")
            .ignoringFields("createdAt")
            .isEqualTo(createOrder().toDomain())
    }

    @Test
    fun shouldHandleOrderNotFound() {
        `when`(orderRepository.findById(1)).thenReturn(Optional.empty())

        val result = findOrderByIdAdapter.execute(1)

        Assertions.assertThat(result).isExactlyInstanceOf(Failure::class.java)
    }
}