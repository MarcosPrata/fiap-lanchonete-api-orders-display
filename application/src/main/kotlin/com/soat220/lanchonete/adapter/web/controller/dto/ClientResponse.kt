package com.soat220.lanchonete.adapter.web.controller.dto

import com.soat220.lanchonete.client.model.Client

data class ClientResponse(

    val id: Long?,
)

fun Client.toClientResponse() = ClientResponse(
    id = id
)