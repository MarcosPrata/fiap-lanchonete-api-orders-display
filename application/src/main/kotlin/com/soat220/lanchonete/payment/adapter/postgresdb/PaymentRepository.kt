package com.soat220.lanchonete.payment.adapter.postgresdb

import com.soat220.lanchonete.payment.adapter.postgresdb.model.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<PaymentEntity, Long> {
}