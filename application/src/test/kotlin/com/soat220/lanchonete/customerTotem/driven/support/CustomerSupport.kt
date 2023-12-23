package com.soat220.lanchonete.customerTotem.driven.support

import com.soat220.lanchonete.common.model.Customer
import org.instancio.Instancio
import org.instancio.Select.field


class CustomerSupport {

    companion object {
        fun createCustomerWithCpf(id: Long?, cpf: String?): Customer {
            return Instancio.of(Customer::class.java)
                .set(field("id"), id)
                .set(field("cpf"), cpf)
                .create()
        }

        fun createCustomer(id: Long?): Customer {
            return Instancio.of(Customer::class.java)
                .set(field("id"), id)
                .set(field("name"), "testname")
                .create()
        }
    }
}