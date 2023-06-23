package com.soat220.lanchonete.order.adapter.postgresdb.model

import com.soat220.lanchonete.customer.adapter.postgresdb.model.CustomerEntity
import com.soat220.lanchonete.order.model.enums.OrderStatus
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.product.adapter.postgresdb.model.ProductEntity
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.streams.toList

@Entity
class OrderEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne @JoinColumn(name = "customer_id")
    var customer: CustomerEntity,

    @ManyToMany
    @JoinTable(
        name = "orders_products",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var products: MutableList<ProductEntity> = mutableListOf(),

    @Enumerated(EnumType.ORDINAL)
    var status: OrderStatus? = OrderStatus.RECEIVED,

    var totalAmount: Double,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime? = null,
    var notes: String

) {

    fun toDomain() = Order(
        id = id,
        customer = customer.toDomain(),
        products = products.stream().map { it.toDomain() }.toList().toMutableList(),
        orderStatus = status,
        createdAt = createdAt,
        updatedAt = updatedAt,
        notes = notes,
        totalAmount = totalAmount
    )

    companion object {
        fun from(customer: CustomerEntity, products: List<ProductEntity>, notes: String) = OrderEntity(
            id = null,
            customer = customer,
            products = products.toMutableList(),
            notes = notes,
            createdAt = LocalDateTime.now(),
            totalAmount = products.sumOf { it.price }
        )
    }

}