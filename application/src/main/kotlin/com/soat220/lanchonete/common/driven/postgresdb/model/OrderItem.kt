package com.soat220.lanchonete.common.driven.postgresdb.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import com.soat220.lanchonete.common.model.OrderItem as DomainOrderItem

@Entity
@Table(name = "orderitem")
class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,

    @Column(name = "amount", nullable = false)
    val amount: Int
) {
    fun toDomain() = DomainOrderItem(
        id = id,
        product = product.toDomain(),
        amount = amount
    )

    companion object {
        fun fromDomain(orderItem: DomainOrderItem) = OrderItem(
            id = orderItem.id,
            product = Product.fromDomain(orderItem.product),
            amount = orderItem.amount
        )
    }
}