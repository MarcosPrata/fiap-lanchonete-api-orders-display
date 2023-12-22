package com.soat220.lanchonete.helper

import com.soat220.lanchonete.customerTotem.usecase.dto.Customer

class CustomerSupport {

    companion object {

        fun createDefault(): Customer {
            return Customer("000.000.000-01", "customer", "unit-test@email.com")
        }

        fun createDomain(dto: Customer?, id: Long): com.soat220.lanchonete.common.model.Customer {
            return com.soat220.lanchonete.common.model.Customer(id, dto?.name, dto?.email, dto?.cpf)
        }

    }

}