package com.soat220.lanchonete.clientTotem.driven.postgresdb.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import com.soat220.lanchonete.clientTotem.model.Order as DomainOrder

@Entity
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne
    private val client: Client,
    @OneToMany
    private val orderItems: MutableList<OrderItem>,
) {
    fun toDomain() = DomainOrder(
        id = id,
        client = client.toDomain(),
        orderItens = orderItems.map { it.toDomain() }.toMutableList(),
    )

    companion object {
        fun fromDomain(order: DomainOrder) = Order(
            id = order.id,
            client = Client.fromDomain(order.client),
            orderItems = order.orderItens.map { OrderItem.fromDomain(it) }.toMutableList()
        )
    }
}