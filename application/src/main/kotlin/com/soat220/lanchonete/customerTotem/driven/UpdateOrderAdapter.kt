package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.OrderRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.exception.NotFoundException
import com.soat220.lanchonete.common.model.enums.OrderStatus
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Product
import com.soat220.lanchonete.kitchen.port.UpdateOrderPort
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class UpdateOrderAdapter(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
) : UpdateOrderPort {

    override fun execute(
        orderId: Long,
        productsIds: List<Long>,
        orderStatus: String,
        notes: String
    ): Result<com.soat220.lanchonete.common.model.Order, DomainException> {

        val order = orderRepository.findById(orderId)
            .orElseThrow { NotFoundException(com.soat220.lanchonete.common.model.Order::class.java) }

        //order.products = retriveProducts(productsIds).toMutableList()
        order.status = OrderStatus.valueOf(orderStatus)
        order.notes = notes
        order.updatedAt = LocalDateTime.now()

        try {
            return Success(orderRepository.save(order).toDomain())
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }

    private fun retriveProducts(ids: List<Long>): List<Product> {
        // TODO utilizar findProductByIdPort ao invés do repository
        return ids
            .map { id ->
                productRepository.findById(id).orElseThrow { NotFoundException(Product.Companion::class.java) }
            }
            .toList()
    }
}