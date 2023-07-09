package com.soat220.lanchonete.kitchen.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.kitchen.port.SetOrderStatusPort
import org.springframework.stereotype.Service

@Service
class SetOrderStatusAdapter(
    private val orderRepository: OrderRepository
) : SetOrderStatusPort {
    override fun execute(orderId: Long, orderStatus: OrderStatus): Result<Order, DomainException> {
        return try {
            val order = orderRepository.findById(orderId).orElseThrow()

            order.status = orderStatus

            Success(orderRepository.save(order).toDomain())
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }
}