package com.soat220.lanchonete.client.model

data class Client(
    var id: Long?,
    var name: String,
    var cpf: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (cpf != other.cpf) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + cpf.hashCode()
        return result
    }

    override fun toString(): String {
        return "Cliente(nome=$name, cpf='$cpf')"
    }

}