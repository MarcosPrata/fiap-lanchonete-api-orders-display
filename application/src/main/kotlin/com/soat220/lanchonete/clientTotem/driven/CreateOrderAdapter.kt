package com.soat220.lanchonete.clientTotem.driven

import com.soat220.lanchonete.clientTotem.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.clientTotem.driven.postgresdb.model.Order
import com.soat220.lanchonete.clientTotem.port.CreateOrderPort
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.erp.exception.CreateProductException
import org.springframework.stereotype.Service
import com.soat220.lanchonete.clientTotem.model.Order as DomainOrder

@Service
class CreateOrderAdapter(
    private val orderRepository: OrderRepository
) : CreateOrderPort {
    override fun execute(order: DomainOrder): Result<DomainOrder, DomainException> {

        try {
            orderRepository.save(Order.fromDomain(order))
        } catch (e: Exception) {
            return Failure(
                CreateProductException(
                    order.id.toString(),
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }

        return Success(order)
    }
}