package com.soat220.lanchonete.payment.adapter

import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.exception.PaymentNotApprovedException
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.FindOrderByIdPort
import com.soat220.lanchonete.order.port.queue.AddToQueuePort
import com.soat220.lanchonete.payment.gateway.port.ProcessPaymentPort
import com.soat220.lanchonete.payment.model.Payment
import com.soat220.lanchonete.payment.model.enums.PaymentStatus
import com.soat220.lanchonete.payment.port.CreatePaymentPort
import com.soat220.lanchonete.payment.port.HandlePaymentPort
import com.soat220.lanchonete.result.orThrow
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class HandlePaymentAdapter(
    private val findOrderByIdPort: FindOrderByIdPort,
    private val createPaymentPort: CreatePaymentPort,
    private val processPaymentPort: ProcessPaymentPort,
    private val addToQueuePort: AddToQueuePort
) : HandlePaymentPort {

    override fun execute(orderId: Long, totalAmount: Double): Payment {

        val order = findOrderByIdPort.execute(orderId).orThrow()

        val paymentStatus =
            if (processPaymentPort.execute(order, totalAmount)) PaymentStatus.APPROVED
            else PaymentStatus.DECLINED

        val payment = createPaymentPort.execute(order, paymentStatus, totalAmount).orThrow()

        if (paymentStatus == PaymentStatus.APPROVED) {
            addToQueuePort.execute(order)
        } else {
            throw PaymentNotApprovedException("Payment not approved", ErrorCode.PAYMENT_NOT_APPROVED)
        }

        return payment
    }
}