package com.soat220.lanchonete.clientTotem.driven.postgresdb

import com.soat220.lanchonete.clientTotem.driven.postgresdb.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long> {
}