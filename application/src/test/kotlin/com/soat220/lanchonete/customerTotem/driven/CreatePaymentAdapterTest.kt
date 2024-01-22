package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.driven.helper.OrderHelper.Companion.createOrder
import com.soat220.lanchonete.customerTotem.driven.postgresdb.PaymentRepository
import com.soat220.lanchonete.customerTotem.driven.postgresdb.model.PaymentEntity
import com.soat220.lanchonete.customerTotem.model.PaymentStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class CreatePaymentAdapterTest {

    private val paymentRepository: PaymentRepository = mock(PaymentRepository::class.java)
    private val createPaymentAdapter: CreatePaymentAdapter = CreatePaymentAdapter(paymentRepository)

    @Test
    fun shouldCreatePaymentSuccessful() {

        doAnswer { it.arguments[0] }.`when`(paymentRepository).save(any(PaymentEntity::class.java))

        val result = createPaymentAdapter.execute(createOrder().toDomain(), PaymentStatus.APPROVED, 50.0)

        assertThat(result).isExactlyInstanceOf(Success::class.java)
    }

    @Test
    fun shouldHandlePaymentException() {
        `when`(paymentRepository.save(any(PaymentEntity::class.java))).thenThrow(RuntimeException::class.java)

        val result = createPaymentAdapter.execute(createOrder().toDomain(), PaymentStatus.APPROVED, 50.0)

        assertThat(result).isExactlyInstanceOf(Failure::class.java)
    }
}