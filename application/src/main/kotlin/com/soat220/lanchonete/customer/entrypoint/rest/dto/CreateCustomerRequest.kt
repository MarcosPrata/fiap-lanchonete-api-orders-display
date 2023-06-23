package com.soat220.lanchonete.customer.entrypoint.rest.dto

import com.soat220.lanchonete.customer.model.Customer

class CreateCustomerRequest (

    private val name: String,
    private val cpf: String

){

    fun toModel() = Customer (
        null,
        name = name,
        cpf = cpf
    )

}