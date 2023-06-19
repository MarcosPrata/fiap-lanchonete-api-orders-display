package com.soat220.lanchonete.domain.port.driver

import com.soat220.lanchonete.domain.model.Client
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface ClientServiceInterface {

    fun save(client: Client): Result<Client, DomainException>

    fun findByCpf(cpf: String): Result<Client, DomainException>
}