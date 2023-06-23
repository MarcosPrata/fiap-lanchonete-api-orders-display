package com.soat220.lanchonete.order.adapter

import com.soat220.lanchonete.order.adapter.postgresdb.OrderRepository
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.FindAllOrdersPort
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class FindAllOrdersAdapter(
    private val orderRepository: OrderRepository
): FindAllOrdersPort {

    override fun execute(): Result<List<Order>, DomainException> {
        return try {

            Success(orderRepository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map { it.toDomain() }
                .toList())

        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }
}