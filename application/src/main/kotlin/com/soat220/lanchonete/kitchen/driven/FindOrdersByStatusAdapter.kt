package com.soat220.lanchonete.kitchen.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.kitchen.port.FindOrdersByStatusPort
import org.springframework.stereotype.Service

@Service
class FindOrdersByStatusAdapter(
    private val orderRepository: OrderRepository
) : FindOrdersByStatusPort {
    override fun execute(orderStatus: OrderStatus): Result<List<Order>, DomainException> {
        return try {
            Success(
                orderRepository.findAllByStatusOrderByCreatedAtAsc(orderStatus).map { it.toDomain() }
            )
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }
}