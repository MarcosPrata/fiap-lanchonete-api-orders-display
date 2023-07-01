package com.soat220.lanchonete.clientTotem.driven.postgresdb.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import com.soat220.lanchonete.clientTotem.model.Client as DomainClient

@Entity
class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    private val cpf: String?,
    private val email: String?,
) {
    fun toDomain() = DomainClient(
        id = id,
        cpf = cpf,
        email = email,
    )

    companion object {
        fun fromDomain(client: DomainClient) = Client(
            id = client.id,
            cpf = client.cpf,
            email = client.email
        )
    }
}