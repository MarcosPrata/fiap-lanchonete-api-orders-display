package com.soat220.lanchonete.customerTotem.usecase

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.exception.NotFoundException
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.result.getOrNull
import com.soat220.lanchonete.customerTotem.port.*
import com.soat220.lanchonete.erp.port.FindProductByIdPort
import com.soat220.lanchonete.helper.CreateOrderSupport
import com.soat220.lanchonete.helper.CustomerSupport
import com.soat220.lanchonete.helper.OrderItemSupport
import com.soat220.lanchonete.helper.ProductSupport
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.kotlin.*

class CreateOrderTest {

    private lateinit var createOrder: CreateOrder

    private val findCustomerByCpfPortMock: FindCustomerByCpfPort = mock()
    private val findProductByIdPortMock: FindProductByIdPort = mock()
    private val createOrderPortMock: CreateOrderPort = mock()
    private val createCustomerPortMock: CreateCustomerPort = mock()
    private val createPaymentPort: CreatePaymentPort = mock()
    private val processPaymentPort: ProcessPaymentPort = mock()

    @BeforeEach
    fun setup() {
        createOrder = CreateOrder(findCustomerByCpfPortMock, findProductByIdPortMock,
            createOrderPortMock, createCustomerPortMock, createPaymentPort, processPaymentPort)
    }

    @Test
    fun shouldCreateOrderSuccessful() {
        // given
        val order = buildCreateOrder(true)

        doReturnProduct()

        doReturnCustomer(order, Success(CustomerSupport.createDomain(order.customer, 1L)))

        answerOrder()

        // when
        val result = createOrder.execute(order)

        // then
        assertThat(result.getOrNull())
            .isNotNull()
            .hasFieldOrPropertyWithValue("orderStatus", OrderStatus.RECEIVED)
            .hasFieldOrPropertyWithValue("notes", "no onions")
        // TODO implementar mais verificações
    }

    @Test
    fun shouldCreateOrderWithoutCustomer() {
        val order = buildCreateOrder(false)

        doReturnProduct()

        answerOrder()

        // when
        val result = createOrder.execute(order)

        // then
        assertThat(result.getOrNull())
            .isNotNull()
            .hasFieldOrPropertyWithValue("orderStatus", OrderStatus.RECEIVED)
            .hasFieldOrPropertyWithValue("notes", "no onions")
            .extracting("customer").containsNull()
    }


    @Test
    fun shouldCreateOrderAndCustomer() {
        // given
        val order = buildCreateOrder(true)

        doReturnProduct()

        doReturnCustomer(order, Failure(DomainException(null, ErrorCode.DATABASE_ERROR)))

        answerCustomer()

        answerOrder()

        // when
        val result = createOrder.execute(order)

        // then
        assertThat(result.getOrNull())
            .isNotNull()
            .hasFieldOrPropertyWithValue("orderStatus", OrderStatus.RECEIVED)
            .hasFieldOrPropertyWithValue("notes", "no onions")
            .hasFieldOrPropertyWithValue("customer", Customer(null ,"customer", "unit-test@email.com", "000.000.000-01"))
    }

    @Test
    fun shouldThrowProductNotFoundException() {
        // given
        val order = buildCreateOrder(true)

        given(findProductByIdPortMock.execute(1L)).willThrow(NotFoundException::class.java)

        // when
        assertThrows<NotFoundException> {
            createOrder.execute(order)
        }

        // then
        verify(createOrderPortMock, never()).execute(any<Order>())
    }

    private fun doReturnProduct() {
        doReturn(Success(ProductSupport.createDefaultProduct())).`when`(findProductByIdPortMock)
            .execute(1L)
    }

    private fun doReturnCustomer(order: com.soat220.lanchonete.customerTotem.usecase.dto.CreateOrder,
                                 result: Result<Customer?, DomainException>) {
        doReturn(result).`when`(findCustomerByCpfPortMock)
            .execute(order.customer?.cpf)
    }

    private fun answerCustomer() {
        doAnswer {

            Success(it.arguments[0])
        }.`when`(createCustomerPortMock).execute(any())
    }

    private fun answerOrder() {
        doAnswer {

            Success(it.arguments[0])

        }.`when`(createOrderPortMock).execute(any())
    }

    private fun buildCreateOrder(isHavingCustomer: Boolean): com.soat220.lanchonete.customerTotem.usecase.dto.CreateOrder {

        var customer: com.soat220.lanchonete.customerTotem.usecase.dto.Customer? = null

        if (isHavingCustomer) {
            customer = CustomerSupport.createDefault()
        }

        val orderItem = OrderItemSupport.createOrderItem(1L, 1)

        return CreateOrderSupport.createOrder(customer, OrderItemSupport.createList(orderItem), "no onions")
    }
}