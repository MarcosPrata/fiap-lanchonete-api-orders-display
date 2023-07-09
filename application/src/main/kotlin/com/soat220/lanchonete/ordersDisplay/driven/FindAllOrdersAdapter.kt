package com.soat220.lanchonete.ordersDisplay.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.ordersDisplay.port.FindAllOrdersPort
import org.springframework.stereotype.Service

@Service
class FindAllOrdersAdapter(
    private val orderRepository: OrderRepository
) : FindAllOrdersPort {
    override fun execute(): Result<List<Order>, DomainException> {
        return try {
            Success(
                orderRepository.findAll().map { it.toDomain() }
            )
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }
}