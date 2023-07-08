package com.soat220.lanchonete.payment.adapter.postgresdb.model

import com.soat220.lanchonete.order.adapter.postgresdb.model.OrderEntity
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.enums.PaymentStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "PAYMENT")
class PaymentEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private val order: OrderEntity,

    @Enumerated(EnumType.STRING)
    private val paymentStatus: PaymentStatus,

    private val totalAmount: Double,

    private val createdAt: LocalDateTime

) {

    fun toDomain() = Payment(
        id = id,
        order = order.toDomain(),
        createdAt = createdAt,
        paymentStatus = paymentStatus,
        totalAmount = totalAmount
    )

    companion object {
        fun from(order: Order, paymentStatus: PaymentStatus, totalAmount: Double) = PaymentEntity(
            order = OrderEntity.fromDomain(order),
            paymentStatus = paymentStatus,
            createdAt = LocalDateTime.now(),
            totalAmount = totalAmount
        )
    }
}