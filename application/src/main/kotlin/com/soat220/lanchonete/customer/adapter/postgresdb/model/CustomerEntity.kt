package com.soat220.lanchonete.customer.adapter.postgresdb.model

import com.soat220.lanchonete.customer.model.Customer
import javax.persistence.*

@Entity
@Table(name = "CUSTOMER")
data class CustomerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var name: String?,
    @Column(unique = true)
    var cpf: String?
) {
    fun toDomain() = Customer(
        id = id,
        name = name,
        cpf = cpf
    )

    companion object {
        fun fromDomain(customer: Customer?) = CustomerEntity(
            customer?.id,
            customer?.name,
            customer?.cpf
        )
    }
}