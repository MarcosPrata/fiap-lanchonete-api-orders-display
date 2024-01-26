package com.soat220.lanchonete.helper

import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.customerTotem.usecase.dto.Customer as CustomerDto

class CustomerHelper {

    companion object {

        fun createCustomer(
            id: Long? = 1,
            name: String? = "Henrique domain",
            email: String? = "henrique.domain@email.com",
            cpf: String? = "000.000.000-01"
        ) = Customer(id = id, name = name, email = email, cpf = cpf )

        fun createCustomerDto(
            name: String? = "Henrique dto",
            email: String? = "henrique.dto@email.com",
            cpf: String? = "000.000.000-02"
        ) = CustomerDto(name = name!!, email = email!!, cpf = cpf!!)

    }
}