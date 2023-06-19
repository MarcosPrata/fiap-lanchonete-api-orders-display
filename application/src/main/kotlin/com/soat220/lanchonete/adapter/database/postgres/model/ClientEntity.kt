package com.soat220.lanchonete.adapter.database.postgres.model

import com.soat220.lanchonete.domain.model.Client
import javax.persistence.*

@Entity
data class ClientEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var name: String,
    @Column
    var cpf: String
) {
    fun toDomain() = Client(
        name = name,
        cpf = cpf
    )

    companion object {
        fun fromDomain(client: Client) = ClientEntity(
            null,
            client.name,
            client.cpf
        )
    }
}