package com.soat220.lanchonete.rest.customer.dto

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