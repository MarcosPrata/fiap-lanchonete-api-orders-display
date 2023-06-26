package com.soat220.lanchonete.order.adapter

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.NotFoundException
import com.soat220.lanchonete.order.adapter.postgresdb.OrderRepository
import com.soat220.lanchonete.order.adapter.postgresdb.model.OrderEntity
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.FindOrderByIdPort
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import org.springframework.stereotype.Service

@Service
class FindOrderByIdAdapter(
    private val orderRepository: OrderRepository
): FindOrderByIdPort {

    override fun execute(orderId: Long): Result<Order, DomainException> {
        return try {
            val order = orderRepository.findById(orderId).orElseThrow { NotFoundException(OrderEntity::class.java) }
            Success(order.toDomain())
        } catch (e: Exception) {
            return Failure(DomainException(e))
        }
    }

}