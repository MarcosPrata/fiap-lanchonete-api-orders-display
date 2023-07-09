package com.soat220.lanchonete.common.driven.postgresdb.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

import com.soat220.lanchonete.common.model.Customer as DomainCustomer

@Entity
@Table(name = "customer")
data class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(unique = true)
    var cpf: String?,
    @Column
    var name: String?,
    @Column
    var email: String?
) {
    fun toDomain() = DomainCustomer(
        id = id,
        cpf = cpf,
        name = name,
        email = email
    )

    companion object {
        fun fromDomain(customer: DomainCustomer?) = Customer(
            id = customer?.id,
            cpf = customer?.cpf,
            name = customer?.name,
            email = customer?.email
        )
    }
}