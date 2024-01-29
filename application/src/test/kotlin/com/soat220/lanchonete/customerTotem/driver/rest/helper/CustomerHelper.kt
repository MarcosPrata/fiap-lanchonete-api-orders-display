package com.soat220.lanchonete.customerTotem.driver.rest.helper

import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.customerTotem.driver.rest.dto.request.CreateCustomerRequest

class CustomerHelper {

    companion object {

        fun createCustomer(
            id: Long? = 1,
            name: String? = "Henrique domain",
            email: String? = "henrique.domain@email.com",
            cpf: String? = "000.000.000-01"
        ) = Customer(id = id, name = name, email = email, cpf = cpf )

        fun createCustomerRequest(
            name: String? = "Henrique domain",
            email: String? = "henrique.domain@email.com",
            cpf: String? = "000.000.000-01"
        ) = CreateCustomerRequest(name = name!!, email = email!!, cpf = cpf!!)

    }
}