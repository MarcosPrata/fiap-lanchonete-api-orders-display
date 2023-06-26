package com.soat220.lanchonete.order.entrypoint.rest

import com.soat220.lanchonete.order.usecase.queue.GetNextOrder
import com.soat220.lanchonete.order.usecase.queue.ListAll
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/orders/queue"], produces = [MediaType.APPLICATION_JSON_VALUE])
class OrderQueueController(
    private val getNextOrder: GetNextOrder,
    private val listAll: ListAll
) {

    @GetMapping
    fun listAll() =
        listAll.execute()


    @GetMapping(value = ["/next"])
    fun getNextOrder() =
        getNextOrder.execute()

}