package com.soat220.lanchonete.adapter.database.postgres

import com.soat220.lanchonete.adapter.database.postgres.model.toEntity
import com.soat220.lanchonete.adapter.database.postgres.repository.ClientRepository
import com.soat220.lanchonete.client.model.Client
import com.soat220.lanchonete.client.ports.driven.ClientPersistenceInterface
import org.springframework.stereotype.Service

@Service
class ClientPersistenceImpl(
        private val clientRepository: ClientRepository
): ClientPersistenceInterface {

    override fun save(client: Client): Client {
        return this.clientRepository.save(client.toEntity()).toClient();
    }
}