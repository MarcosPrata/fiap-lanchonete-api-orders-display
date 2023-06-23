package com.soat220.lanchonete.postgres.repository

import com.soat220.lanchonete.postgres.model.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository  : JpaRepository<CustomerEntity, Long> {

    fun findByCpf(cpf: String): CustomerEntity

}