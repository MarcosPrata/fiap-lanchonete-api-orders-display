package com.soat220.lanchonete.customerTotem.driver.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.driver.rest.helper.OrderHelper.Companion.createOrder
import com.soat220.lanchonete.customerTotem.driver.rest.helper.OrderHelper.Companion.createOrderRequest
import com.soat220.lanchonete.customerTotem.driver.rest.helper.ProductHelper.Companion.createProduct
import com.soat220.lanchonete.customerTotem.usecase.CreateOrder
import com.soat220.lanchonete.customerTotem.usecase.ListProducts
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class TotemControllerIT {

    private lateinit var mockMvc: MockMvc

    private var createOrder = mock(CreateOrder::class.java)

    private var listProducts = mock(ListProducts::class.java)

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = standaloneSetup(TotemController(createOrder, listProducts))
           .build()
    }

    @Test
    fun shouldReturnSuccessfulWhenCreateOrder() {
        val orderRequest = createOrderRequest()

        doReturn(Success(createOrder()))
            .whenever(createOrder).execute(any())

        mockMvc.post("/api/customer-totem/orders") {
            content = mapper.writeValueAsString(orderRequest)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect { status { isOk() } }
    }

    @Test
    fun shouldReturnSuccessfulWhenListProducts() {

        val products = getProducts()

        doReturn(Success(products))
            .whenever(listProducts).execute()

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer-totem/products"))
            .andExpect(status().isOk)
    }

    private fun getProducts() = listOf(
        createProduct(),
        createProduct(id = 2),
        createProduct(id = 3),
    )

}