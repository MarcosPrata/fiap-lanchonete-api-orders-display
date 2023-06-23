package com.soat220.lanchonete.order.entrypoint.rest

import com.soat220.lanchonete.order.entrypoint.rest.dto.CreateOrderRequest
import com.soat220.lanchonete.order.entrypoint.rest.dto.UpdateOrderRequest
import com.soat220.lanchonete.order.usecase.CreateOrder
import com.soat220.lanchonete.order.usecase.FindAllOrders
import com.soat220.lanchonete.order.usecase.UpdateOrder
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/orders"], produces = [MediaType.APPLICATION_JSON_VALUE])
class OrderController(
    private val createOrder: CreateOrder,
    private val updateOrder: UpdateOrder,
    private val findAllOrders: FindAllOrders
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody createOrderRequest: CreateOrderRequest) =
        createOrder.execute(createOrderRequest.customerCpf, createOrderRequest.productsIds, createOrderRequest.notes)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody updateOrderRequest: UpdateOrderRequest) =
        updateOrder.execute(updateOrderRequest.orderId, updateOrderRequest.productsIds, updateOrderRequest.status, updateOrderRequest.notes)

    @GetMapping
    fun findAll() = findAllOrders.execute()

}