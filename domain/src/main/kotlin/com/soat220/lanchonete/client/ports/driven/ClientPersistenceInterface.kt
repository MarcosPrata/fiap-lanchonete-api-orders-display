package com.soat220.lanchonete.client.ports.driven

import com.soat220.lanchonete.client.model.Client

interface ClientPersistenceInterface {

    fun save(client: Client): Client
}