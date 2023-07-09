package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.customerTotem.model.Payment
import com.soat220.lanchonete.customerTotem.model.PaymentStatus
import com.soat220.lanchonete.customerTotem.port.CreatePaymentPort
import com.soat220.lanchonete.customerTotem.port.HandlePaymentPort
import com.soat220.lanchonete.customerTotem.port.ProcessPaymentPort
import com.soat220.lanchonete.kitchen.port.FindOrderByIdPort
import org.springframework.stereotype.Service

@Service
class HandlePaymentAdapter(
    private val findOrderByIdPort: FindOrderByIdPort,
    private val createPaymentPort: CreatePaymentPort,
    private val processPaymentPort: ProcessPaymentPort,
    //private val addToQueuePort: AddToQueuePort
) : HandlePaymentPort {

    override fun execute(orderId: Long, totalAmount: Double): Payment {

        val order = findOrderByIdPort.execute(orderId).orThrow()

        val paymentStatus =
            if (processPaymentPort.execute(order, totalAmount)) PaymentStatus.APPROVED
            else PaymentStatus.DECLINED

        val payment = createPaymentPort.execute(order, paymentStatus, totalAmount).orThrow()

//        if (paymentStatus == PaymentStatus.APPROVED) {
//            addToQueuePort.execute(order)
//        } else {
//            throw PaymentNotApprovedException("Payment not approved", ErrorCode.PAYMENT_NOT_APPROVED)
//        }

        return payment
    }
}