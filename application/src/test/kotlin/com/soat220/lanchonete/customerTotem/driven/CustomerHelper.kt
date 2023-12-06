package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.model.Customer

class CustomerHelper {

    companion object {
        fun createCustomer(id: Long, cpf: String): Customer {
            return Customer(id, "customer name", "customer@email.com", cpf);
        }
    }
}