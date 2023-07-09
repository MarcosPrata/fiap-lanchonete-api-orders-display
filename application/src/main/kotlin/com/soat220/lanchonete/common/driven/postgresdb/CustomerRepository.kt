package com.soat220.lanchonete.common.driven.postgresdb

import com.soat220.lanchonete.common.driven.postgresdb.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository  : JpaRepository<Customer, Long> {
    fun findByCpf(cpf: String?): Customer
}