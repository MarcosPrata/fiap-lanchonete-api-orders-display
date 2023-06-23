package com.soat220.lanchonete.customer.adapter.postgresdb

import com.soat220.lanchonete.customer.adapter.postgresdb.model.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository  : JpaRepository<CustomerEntity, Long> {

    fun findByCpf(cpf: String): CustomerEntity

}