package com.soat220.lanchonete.adapter.web.controller

import com.soat220.lanchonete.adapter.web.controller.dto.ClientRequest
import com.soat220.lanchonete.adapter.web.controller.dto.ClientResponse
import com.soat220.lanchonete.adapter.web.controller.dto.toClientResponse
import com.soat220.lanchonete.client.ports.driver.ClientServiceInterface
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/clients"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientController (

    private val clientService : ClientServiceInterface

){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody clientRequest: ClientRequest): ClientResponse =
        clientService.saveClient(clientRequest.toModel()).toClientResponse()

}