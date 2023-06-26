package com.soat220.lanchonete.payment.entrypoint.rest

import com.soat220.lanchonete.payment.entrypoint.rest.dto.ProcessPaymentRequest
import com.soat220.lanchonete.payment.port.HandlePaymentPort
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/payments"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PaymentController(
    private val handlePaymentPort: HandlePaymentPort
) {
    @PostMapping
    fun process(@RequestBody processPaymentRequest: ProcessPaymentRequest) {
        handlePaymentPort.execute(processPaymentRequest.orderId, processPaymentRequest.totalAmount)
    }

}