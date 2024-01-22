package com.soat220.lanchonete.customerTotem.driven.helper

import com.soat220.lanchonete.common.driven.postgresdb.model.Customer

class CustomerHelper {

    companion object {

        fun createCustomer(
            id: Long? = 1,
            cpf: String? = "000.000.000-01",
            name: String? = "Henrique",
            email: String? = "henrique.teste@email.com"
        ) = Customer(id = id, cpf = cpf, name = name, email = email)

    }
}
