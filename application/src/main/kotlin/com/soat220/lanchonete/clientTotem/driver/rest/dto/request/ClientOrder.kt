package com.soat220.lanchonete.clientTotem.driver.rest.dto.request

import com.soat220.lanchonete.clientTotem.model.Client

data class ClientOrder(
    val clientEmail: String,
    val clientCPF: String
) {
    fun toDomain() = Client(
        cpf = clientCPF,
        email = clientEmail,
    )
}