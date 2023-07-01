package com.soat220.lanchonete.clientTotem.driver.rest

import com.soat220.lanchonete.clientTotem.usecase.CreateOrder
import com.soat220.lanchonete.clientTotem.usecase.ListProducts
import com.soat220.lanchonete.common.result.orThrow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.soat220.lanchonete.clientTotem.driver.rest.dto.request.CreateOrder as CreateOrderRequest

@RestController
@RequestMapping(value = ["/api/totem"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TotemController(
    private val createOrder: CreateOrder,
    private val listProducts: ListProducts,
) {
    @PostMapping("/orders")
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest) =
        this.createOrder.execute(createOrderRequest.toDomain()).orThrow()

    @GetMapping("/products")
    fun findProducts() = listProducts.execute().orThrow()
}