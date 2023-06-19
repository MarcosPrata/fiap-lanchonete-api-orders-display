package com.soat220.lanchonete.adapter.entrypoint.rest.client.dto

import com.soat220.lanchonete.domain.model.Client

class CreateClientRequest (

    private val name: String,
    private val cpf: String

){

    fun toModel() = Client (
        name = name,
        cpf = cpf
    )

}