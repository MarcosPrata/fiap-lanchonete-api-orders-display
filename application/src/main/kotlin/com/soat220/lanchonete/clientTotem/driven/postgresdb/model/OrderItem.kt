package com.soat220.lanchonete.clientTotem.driven.postgresdb.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import com.soat220.lanchonete.clientTotem.model.OrderItem as DomainOrderItem

@Entity
class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    private val productId: Long,
    private val ammount: Int,
    private val note: String
) {
    fun toDomain() = DomainOrderItem(
        id = id,
        productId = productId,
        ammount = ammount,
        note = note
    )

    companion object {
        fun fromDomain(orderItem: DomainOrderItem) = OrderItem(
            id = orderItem.id,
            productId = orderItem.productId,
            ammount = orderItem.ammount,
            note = orderItem.note
        )
    }
}