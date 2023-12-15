package com.soat220.lanchonete.ordersDisplay.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.OrderItem
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.model.enums.Category
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.ordersDisplay.port.FindAllOrdersPort
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.time.LocalDateTime

class FindAllOrdersTest {

    private lateinit var findAllOrdersMock: FindAllOrdersPort
    private lateinit var findAllOrders: FindAllOrders

    @BeforeEach
    fun setup() {
        this.findAllOrdersMock = mock()
        this.findAllOrders = FindAllOrders(findAllOrdersMock)
    }

    @Test
    fun shouldReturnOrdersSuccessful() {

        val orders = getOrdersList(OrderStatus.RECEIVED, OrderStatus.RECEIVED)
        given(findAllOrdersMock.execute()).willReturn(Success(orders))

        val result = this.findAllOrders.execute()

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull()).isEqualTo(orders)
    }

    @Test
    fun shouldReturnOnlyOrderReceived() {
        val orders = getOrdersList(OrderStatus.RECEIVED, OrderStatus.COMPLETED)

        given(findAllOrdersMock.execute()).willReturn(Success(orders))

        val result = this.findAllOrders.execute()

        assertThat(result).isExactlyInstanceOf(Success::class.java)
        assertThat(result.getOrNull())
            .hasSize(1)
            .isEqualTo(listOf(orders[0]))
    }

    @Test
    fun shouldHandleDomainException() {
        given(findAllOrdersMock.execute()).willThrow(DomainException::class.java)

        assertThrows<DomainException> {

            val result = findAllOrders.execute();

            assertThat(result).isExactlyInstanceOf(Failure::class.java)
        }
    }

    private fun getOrdersList(orderStatus: OrderStatus, orderStatus2: OrderStatus): List<Order> {

        val customer = Customer(1L, "customer1", "customer@email", "000.000.000-01")
        val product = Product(1L, "product1", "description", Category.SANDWICH, 1.0, emptyList(), false)
        val orderItem = OrderItem(1L, product, 1)

        val order = Order(1L, customer, mutableListOf(orderItem), orderStatus, "notes", LocalDateTime.now(), LocalDateTime.now())
        val order2 = Order(2L, customer, mutableListOf(orderItem), orderStatus2, "notes2", LocalDateTime.now(), LocalDateTime.now());

        return listOf(order, order2)
    }
}