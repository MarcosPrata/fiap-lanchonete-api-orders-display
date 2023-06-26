package com.soat220.lanchonete.payment

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.payment.adapter.postgresdb.PaymentRepository
import com.soat220.lanchonete.payment.adapter.postgresdb.model.PaymentEntity
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.enums.PaymentStatus
import com.soat220.lanchonete.payment.port.CreatePaymentPort
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import org.springframework.stereotype.Service

@Service
class CreatePaymentAdapter(
    private val paymentRepository: PaymentRepository
): CreatePaymentPort {

    override fun execute(order: Order, paymentStatus: PaymentStatus, totalAmount: Double): Result<Payment, DomainException> {
        return try {
            val payment = paymentRepository.save(PaymentEntity.from(order, paymentStatus, totalAmount)).toDomain()
            Success(payment)
        } catch (e: Exception) {
            return Failure(DomainException(e))
        }
    }

}