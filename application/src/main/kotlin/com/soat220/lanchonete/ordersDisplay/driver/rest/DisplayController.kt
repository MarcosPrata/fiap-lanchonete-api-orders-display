package com.soat220.lanchonete.ordersDisplay.driver.rest

import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.ordersDisplay.usecase.FindAllOrders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/orders-display"], produces = [MediaType.APPLICATION_JSON_VALUE])
class DisplayController(
    private val findAllOrders: FindAllOrders,
) {
    @GetMapping("/orders")
    fun findOrders() = findAllOrders.execute().orThrow()
}