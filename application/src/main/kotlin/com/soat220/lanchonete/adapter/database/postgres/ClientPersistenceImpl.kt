package com.soat220.lanchonete.adapter.database.postgres

import com.soat220.lanchonete.adapter.database.postgres.model.ClientEntity
import com.soat220.lanchonete.adapter.database.postgres.repository.ClientRepository
import com.soat220.lanchonete.domain.model.Client
import com.soat220.lanchonete.domain.port.driven.ClientPersistenceInterface
import org.springframework.stereotype.Service

@Service
class ClientPersistenceImpl(
    private val clientRepository: ClientRepository
) : ClientPersistenceInterface {

    override fun save(client: Client): Client {
        return this.clientRepository.save(ClientEntity.fromDomain(client)).toDomain();
    }

    override fun findByCpf(cpf: String): Client {
        return this.clientRepository.findByCpf(cpf).toDomain()
    }
}