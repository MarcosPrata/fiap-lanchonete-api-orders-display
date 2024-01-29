package com.soat220.lanchonete.erp.driver.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.erp.driver.rest.helper.ProductHelper.Companion.createProductModel
import com.soat220.lanchonete.erp.driver.rest.helper.ProductHelper.Companion.createProductRequest
import com.soat220.lanchonete.erp.driver.rest.helper.ProductHelper.Companion.updateProductRequest
import com.soat220.lanchonete.erp.usecase.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ProductControllerIT {

    private lateinit var mockMvc: MockMvc

    private val createProduct = mock(CreateProduct::class.java)
    private val findProducts = mock(FindProducts::class.java)
    private val findProductById = mock(FindProductById::class.java)
    private val updateProduct = mock(UpdateProduct::class.java)
    private val deleteProduct = mock(DeleteProduct::class.java)

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(
            ProductController(createProduct, findProducts, findProductById, updateProduct, deleteProduct)
        ).build()
    }

    @Test
    fun shouldReturnSuccessfulWhenCreateProduct() {
        val productRequest = createProductRequest()

        doReturn(Success(createProductModel()))
            .whenever(createProduct).execute(any())

        val result = mockMvc.post("/api/erp/products") {
            content = mapper.writeValueAsString(productRequest)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect { status { isOk() } }.andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, Product::class.java))
            .usingRecursiveComparison()
            .isEqualTo(createProductModel())
    }

    @Test
    fun shouldFindProductsSuccessful() {
        val products = getProducts()

        doReturn(Success(products))
            .whenever(findProducts).execute()

        val result = mockMvc.perform(get("/api/erp/products", 1))
            .andExpect(status().isOk)
            .andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, List::class.java))
            .asList()
            .hasSize(3)
    }

    @Test
    fun shouldReturnSuccessfulWhenFindProductById() {

        doReturn(Success(createProductModel()))
            .whenever(findProductById).execute(1)

        val result = mockMvc.perform(get("/api/erp/products/{productId}", 1))
            .andExpect(status().isOk)
            .andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, Product::class.java))
            .usingRecursiveComparison()
            .isEqualTo(createProductModel())
    }

    @Test
    fun shouldReturnSuccessfulWhenUpdateProduct() {

        val updateProductRequest = updateProductRequest()

        doReturn(Success(createProductModel()))
            .whenever(updateProduct).execute(any())

        val result = mockMvc.perform(put("/api/erp/products/{productId}", 1)
                .content(mapper.writeValueAsString(updateProductRequest))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andReturn()

        assertThat(mapper.readValue(result.response.contentAsString, Product::class.java))
            .usingRecursiveComparison()
            .isEqualTo(createProductModel())
    }

    @Test
    fun shouldReturnSuccessfulWhenDeleteProduct() {
        doReturn(Success(Unit)).whenever(deleteProduct).execute(1)

        mockMvc.perform(delete("/api/erp/products/{productId}", 1))
            .andExpect(status().isOk)
    }

    private fun getProducts() = listOf(
        createProductModel(),
        createProductModel(id = 2),
        createProductModel(id = 3)
    )

}