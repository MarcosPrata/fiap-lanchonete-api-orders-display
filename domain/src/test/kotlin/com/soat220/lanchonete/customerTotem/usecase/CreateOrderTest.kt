package com.soat220.lanchonete.customerTotem.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.port.CreateCustomerPort
import com.soat220.lanchonete.customerTotem.port.CreateOrderPort
import com.soat220.lanchonete.customerTotem.port.FindCustomerByCpfPort
import com.soat220.lanchonete.customerTotem.usecase.dto.Customer
import com.soat220.lanchonete.erp.port.FindProductByIdPort
import com.soat220.lanchonete.helper.CustomerHelper.Companion.createCustomerDto
import com.soat220.lanchonete.helper.OrderHelper
import com.soat220.lanchonete.helper.ProductHelper.Companion.createProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import com.soat220.lanchonete.customerTotem.usecase.dto.CreateOrder as CreateOrderDto
import com.soat220.lanchonete.customerTotem.usecase.dto.OrderItem as OrderItemDto

class CreateOrderTest {

    private val findCustomerByCpfPort = mock(FindCustomerByCpfPort::class.java)
    private val findProductByIdPort = mock(FindProductByIdPort::class.java)
    private val createOrderPort = mock(CreateOrderPort::class.java)
    private val createCustomerPort = mock(CreateCustomerPort::class.java)

    private val createOrder = CreateOrder(findCustomerByCpfPort, findProductByIdPort, createOrderPort, createCustomerPort)

    @Test
    fun shouldCreateOrderWithCustomer() {

        val notes = "Sem cebola"
        val orderItems = getOrderItemsDto()
        val customer = createCustomerDto("000.000.000-02")

        mockValidProducts()
        mockEmptyCustomer()

        doReturnCustomer()
        doReturnOrder()

        val result = runScenario(customer, orderItems, notes)

        assertThat(result.getOrNull()).isNotNull()
        assertThat(result.getOrNull())
            .hasFieldOrProperty("customer")
            .hasFieldOrPropertyWithValue("orderStatus", OrderStatus.RECEIVED)
            .hasFieldOrPropertyWithValue("notes", notes)
    }

    @Test
    fun shouldCreateOrderWithoutCustomer() {
        val notes = "Sem cebola"
        val orderItems = getOrderItemsDto()

        mockValidProducts()
        doReturnOrder()

        val result = runScenario(null, orderItems, notes)

        assertThat(result.getOrNull()).isNotNull()
        assertThat(result.getOrNull())
            .hasFieldOrPropertyWithValue("customer", null)
            .hasFieldOrPropertyWithValue("orderStatus", OrderStatus.RECEIVED)
            .hasFieldOrPropertyWithValue("notes", notes)
    }

    private fun runScenario(customer: Customer?, orderItems: List<OrderItemDto>, notes: String): Result<Order, DomainException> {
        return createOrder.execute(CreateOrderDto(customer = customer, orderItems = orderItems, notes = notes))
    }

    private fun <T> any() : T {
        return org.mockito.ArgumentMatchers.any()
    }

    private fun doReturnOrder() {
        doAnswer { Success(it.arguments[0]) }.`when`(createOrderPort).execute(this.any())
    }

    private fun doReturnCustomer() {
        doAnswer { Success(it.arguments[0]) }.`when`(createCustomerPort).execute(this.any())
    }

    private fun mockEmptyCustomer() {
        `when`(findCustomerByCpfPort.execute("000.000.000-02")).thenReturn(Success(null))
    }

    private fun mockValidProducts() {
        `when`(findProductByIdPort.execute(1)).thenReturn(Success(createProduct()))
        `when`(findProductByIdPort.execute(2)).thenReturn(Success(createProduct(id = 2)))
    }

    private fun getOrderItemsDto(): List<OrderItemDto> {
        return mutableListOf(
            OrderHelper.createOrderItemDto(),
            OrderHelper.createOrderItemDto(productId = 2)
        )
    }

}