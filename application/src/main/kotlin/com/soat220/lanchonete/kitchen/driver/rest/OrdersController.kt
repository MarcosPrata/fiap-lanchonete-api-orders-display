package com.soat220.lanchonete.kitchen.driver.rest

import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.kitchen.usecase.FindPreparingOrders
import com.soat220.lanchonete.kitchen.usecase.FindReceivedOrders
import com.soat220.lanchonete.kitchen.usecase.FinishOrder
import com.soat220.lanchonete.kitchen.usecase.PrepareOrder
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/kitchen"], produces = [MediaType.APPLICATION_JSON_VALUE])
class OrdersController(
    private val findReceivedOrders: FindReceivedOrders,
    private val findPreparingOrders: FindPreparingOrders,
    private val prepareOrder: PrepareOrder,
    private val finishOrder: FinishOrder,
) {
    @GetMapping("/orders/received")
    fun findReceivedOrders() = findReceivedOrders.execute().orThrow()

    @GetMapping("/orders/preparing")
    fun findPreparingOrders() = findPreparingOrders.execute().orThrow()

    @PatchMapping("/orders/{orderId}/prepare")
    fun prepareOrder(@PathVariable orderId: Long) = prepareOrder.execute(orderId).orThrow()

    @PatchMapping("/orders/{orderId}/finish")
    fun finishOrder(@PathVariable orderId: Long) = finishOrder.execute(orderId).orThrow()
}