package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.driven.postgresdb.PaymentRepository
import com.soat220.lanchonete.customerTotem.driven.postgresdb.model.PaymentEntity
import com.soat220.lanchonete.customerTotem.model.Payment
import com.soat220.lanchonete.customerTotem.model.PaymentStatus
import com.soat220.lanchonete.customerTotem.port.CreatePaymentPort
import org.springframework.stereotype.Service

@Service
class CreatePaymentAdapter(
    private val paymentRepository: PaymentRepository
) : CreatePaymentPort {

    override fun execute(
        order: Order,
        paymentStatus: PaymentStatus,
        totalAmount: Double
    ): Result<Payment, DomainException> {
        return try {
            val payment = paymentRepository.save(PaymentEntity.from(order, paymentStatus, totalAmount)).toDomain()
            Success(payment)
        } catch (e: Exception) {
            return Failure(DomainException(e))
        }
    }

}