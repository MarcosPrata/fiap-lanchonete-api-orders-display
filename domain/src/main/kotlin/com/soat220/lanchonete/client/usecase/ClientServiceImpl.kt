package com.soat220.lanchonete.client.usecase

import com.soat220.lanchonete.client.model.Client
import com.soat220.lanchonete.client.ports.driven.ClientPersistenceInterface
import com.soat220.lanchonete.client.ports.driver.ClientServiceInterface
import com.soat220.lanchonete.exception.CreateClientException
import javax.inject.Named

@Named
class ClientServiceImpl(

    private val clientPersistence: ClientPersistenceInterface

): ClientServiceInterface {

    override fun saveClient(client: Client): Client {
        if (!client.isValid()) {
            throw CreateClientException("Client is not valid")
        }
        return clientPersistence.save(client)
    }
}