package com.soat220.lanchonete.adapter.database.postgres.model

import com.soat220.lanchonete.client.model.Client
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ClientEntity (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var name: String,
        @Column
        var cpf: String
) {
    fun toClient() = Client(
            id = id,
            name = name,
            cpf = cpf
    )
}

fun Client.toEntity() = ClientEntity(
        name = name ?: "",
        cpf = cpf
)