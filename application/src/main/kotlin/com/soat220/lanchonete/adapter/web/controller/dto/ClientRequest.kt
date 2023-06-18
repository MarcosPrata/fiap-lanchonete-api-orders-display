package com.soat220.lanchonete.adapter.web.controller.dto

import com.soat220.lanchonete.client.model.Client

class ClientRequest (

    val name: String,
    val cpf: String

){

    fun toModel() = Client (
        null,
        name = name,
        cpf = cpf
    )

}