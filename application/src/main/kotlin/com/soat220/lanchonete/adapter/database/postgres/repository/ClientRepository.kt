package com.soat220.lanchonete.adapter.database.postgres.repository

import com.soat220.lanchonete.adapter.database.postgres.model.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository  : JpaRepository<ClientEntity, String> {
}