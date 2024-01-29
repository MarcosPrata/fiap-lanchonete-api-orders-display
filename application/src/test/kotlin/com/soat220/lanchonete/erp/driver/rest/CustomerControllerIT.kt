package com.soat220.lanchonete.erp.driver.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.driver.rest.helper.CustomerHelper.Companion.createCustomer
import com.soat220.lanchonete.erp.usecase.FindAllCustomers
import com.soat220.lanchonete.erp.usecase.FindCustomerById
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

class CustomerControllerIT {

    private lateinit var mockMvc: MockMvc

    private val findCustomers: FindAllCustomers = mock(FindAllCustomers::class.java)
    private val findCustomerById: FindCustomerById = mock(FindCustomerById::class.java)

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(CustomerController(findCustomers, findCustomerById))
            .build()
    }

    @Test
    fun shouldReturnSuccessfulWhenFindAllCustomers() {

        val customers = getCustomers()

        doReturn(Success(customers))
            .whenever(findCustomers).execute()

        mockMvc.perform(MockMvcRequestBuilders.get("/api/erp/customers"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun shouldReturnSuccessfulWhenFindCustomerById() {

        doReturn(Success(createCustomer()))
            .whenever(findCustomerById).execute(1)

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/api/erp/customers/{customerId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, Customer::class.java))
            .isEqualTo(createCustomer())
    }

    private fun getCustomers() = listOf(
        createCustomer(),
        createCustomer(id = 2),
        createCustomer(id = 3)
    )
}