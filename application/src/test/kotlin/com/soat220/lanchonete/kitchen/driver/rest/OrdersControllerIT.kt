package com.soat220.lanchonete.kitchen.driver.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.kitchen.driven.helper.OrderHelper.Companion.createOrder
import com.soat220.lanchonete.kitchen.usecase.FindPreparingOrders
import com.soat220.lanchonete.kitchen.usecase.FindReceivedOrders
import com.soat220.lanchonete.kitchen.usecase.FinishOrder
import com.soat220.lanchonete.kitchen.usecase.PrepareOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class OrdersControllerIT {

    private lateinit var mockMvc: MockMvc

    private val findReceivedOrders = mock(FindReceivedOrders::class.java)
    private val findPreparingOrders = mock(FindPreparingOrders::class.java)
    private val prepareOrder = mock(PrepareOrder::class.java)
    private val finishOrder = mock(FinishOrder::class.java)

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(OrdersController(findReceivedOrders, findPreparingOrders, prepareOrder, finishOrder))
            .build()

        mapper.registerModule(JavaTimeModule())
    }

    @Test
    fun shouldReturnReceivedOrdersSuccessful() {

        val ordersReceived = getOrdersWithStatus(OrderStatus.RECEIVED)

        doReturn(Success(ordersReceived))
            .whenever(findReceivedOrders).execute()

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/api/kitchen/orders/received"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, object : TypeReference<List<Order>>() {}))
            .hasSize(3) //TODO compare order status
//            .contains(createOrder(orderStatus = OrderStatus.RECEIVED).toDomain())
    }

    @Test
    fun shouldReturnPreparingOrdersSuccessful() {

        val ordersReceived = getOrdersWithStatus(OrderStatus.IN_PREPARATION)

        doReturn(Success(ordersReceived))
            .whenever(findPreparingOrders).execute()

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/api/kitchen/orders/preparing"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, object : TypeReference<List<Order>>() {}))
            .hasSize(3) //TODO compare order status
//            .contains(createOrder(orderStatus = OrderStatus.RECEIVED).toDomain())

    }

    fun getOrdersWithStatus(orderStatus: OrderStatus) = listOf(
        createOrder(orderStatus = orderStatus).toDomain(),
        createOrder(id = 2, orderStatus = orderStatus).toDomain(),
        createOrder(id = 3, orderStatus = orderStatus).toDomain()
    )

}