package com.soat220.lanchonete.client.ports.driver

import com.soat220.lanchonete.client.model.Client

interface ClientServiceInterface {

    fun saveClient(client: Client): Client
}