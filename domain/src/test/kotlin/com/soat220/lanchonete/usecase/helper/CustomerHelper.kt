package com.soat220.lanchonete.helper

import com.soat220.lanchonete.common.model.Customer

class CustomerHelper {

    companion object {

        fun createCustomer(
            id: Long? = 1,
            name: String? = "Henrique domain",
            email: String? = "henrique.domain@email.com",
            cpf: String? = "000.000.000-01"
        ) = Customer(id = id, name = name, email = email, cpf = cpf )

    }
}