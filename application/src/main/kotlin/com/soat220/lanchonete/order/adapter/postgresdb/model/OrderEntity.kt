package com.soat220.lanchonete.order.adapter.postgresdb.model

import com.soat220.lanchonete.customer.adapter.postgresdb.model.CustomerEntity
import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.order.model.enums.OrderStatus
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.product.adapter.postgresdb.model.ProductEntity
import com.soat220.lanchonete.product.model.Product
import java.time.LocalDateTime
import java.util.Objects.nonNull
import javax.persistence.*
import kotlin.streams.toList

@Entity
@Table(name = "ORDERS")
class OrderEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne @JoinColumn(name = "customer_id")
    var customer: CustomerEntity? = null,

    @ManyToMany
    @JoinTable(
        name = "orders_products",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var products: MutableList<ProductEntity> = mutableListOf(),

    @Enumerated(EnumType.ORDINAL)
    var status: OrderStatus? = OrderStatus.RECEIVED,

    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime? = null,
    var notes: String

) {

    fun toDomain() = Order(
        id = id,
        customer = customer?.toDomain(),
        products = products.stream().map { it.toDomain() }.toList().toMutableList(),
        orderStatus = status,
        createdAt = createdAt,
        updatedAt = updatedAt,
        notes = notes,
    )

    companion object {
        fun from(customer: Customer?, products: MutableList<ProductEntity>, notes: String) = OrderEntity(
            id = null,
            customer = if (nonNull(customer)) CustomerEntity.fromDomain(customer) else null,
            products = products,
            notes = notes,
            createdAt = LocalDateTime.now()
        )

        fun fromDomain(order: Order) = OrderEntity(
            id = order.id,
            customer = CustomerEntity.fromDomain(order.customer),
            products = order.products?.stream()?.map { it -> ProductEntity.fromDomain(it) }?.toList()?.toMutableList()
                ?: mutableListOf(),
            status = order.orderStatus,
            createdAt = order.createdAt,
            updatedAt = order.updatedAt,
            notes = order.notes
        )
    }

}