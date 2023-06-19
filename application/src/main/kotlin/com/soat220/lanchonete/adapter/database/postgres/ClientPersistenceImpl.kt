package com.soat220.lanchonete.adapter.database.postgres

import com.soat220.lanchonete.adapter.database.postgres.model.ClientEntity
import com.soat220.lanchonete.adapter.database.postgres.repository.ClientRepository
import com.soat220.lanchonete.domain.model.Client
import com.soat220.lanchonete.port.driven.DomainPersistenceInterface
import org.springframework.stereotype.Service

@Service
class ClientPersistenceImpl(
    private val clientRepository: ClientRepository
) : DomainPersistenceInterface<Client> {

    override fun save(domain: Client): Client {
        return this.clientRepository.save(ClientEntity.fromDomain(domain)).toDomain();
    }
}