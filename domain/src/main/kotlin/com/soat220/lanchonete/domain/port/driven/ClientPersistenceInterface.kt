package com.soat220.lanchonete.domain.port.driven

import com.soat220.lanchonete.domain.model.Client

interface ClientPersistenceInterface {

    fun save(client: Client): Client

    fun findByCpf(cpf: String): Client
}