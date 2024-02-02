package com.soat220.lanchonete.ordersDisplay.driver.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.ordersDisplay.driven.helper.OrderHelper.Companion.createOrder
import com.soat220.lanchonete.ordersDisplay.usecase.FindAllOrders
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

class DisplayControllerIT {

    private lateinit var mockMvc: MockMvc

    private val findAllOrders: FindAllOrders = mock(FindAllOrders::class.java)

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(DisplayController(findAllOrders))
            .build()
        mapper.registerModule(JavaTimeModule())
    }

    @Test
    fun shouldReturnAllOrdersSuccessful() {
        val orders = getOrders()

        doReturn(Success(orders))
            .whenever(findAllOrders).execute()

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/api/orders-display/orders"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, object : TypeReference<List<Order>>() {}))
            .asList()
            .hasSize(3) //TODO
    }

    private fun getOrders() = listOf(
        createOrder().toDomain(),
        createOrder(id = 2).toDomain(),
        createOrder(id = 3).toDomain()
    )
}