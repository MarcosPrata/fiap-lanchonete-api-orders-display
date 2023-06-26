package com.soat220.lanchonete.payment.entrypoint.rest.dto

class ProcessPaymentRequest(
    internal val orderId: Long,
    internal val totalAmount: Double
) {
}