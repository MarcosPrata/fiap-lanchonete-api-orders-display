package com.soat220.lanchonete.order.adapter

import com.soat220.lanchonete.order.model.enums.OrderStatus
import com.soat220.lanchonete.order.adapter.postgresdb.model.OrderEntity
import com.soat220.lanchonete.order.adapter.postgresdb.OrderRepository
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.UpdateOrderPort
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.exception.NotFoundException
import com.soat220.lanchonete.product.adapter.postgresdb.ProductRepository
import com.soat220.lanchonete.product.adapter.postgresdb.model.ProductEntity
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateOrderAdapter (
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
): UpdateOrderPort {

    override fun execute(orderId: Long, productsIds: List<Long>, orderStatus: String, notes: String): Result<Order, DomainException> {

        val orderEntity = orderRepository.findById(orderId).orElseThrow { NotFoundException(OrderEntity.Companion::class.java) }

        orderEntity.products = retriveProducts(productsIds).toMutableList()
        orderEntity.status = OrderStatus.valueOf(orderStatus)
        orderEntity.notes = notes
        orderEntity.updatedAt = LocalDateTime.now()

        try {
            return Success(orderRepository.save(orderEntity).toDomain())
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }

    private fun retriveProducts(ids: List<Long>): List<ProductEntity> {
        // TODO utilizar findProductByIdPort ao invés do repository
        return ids
            .map {
                    id -> productRepository.findById(id).orElseThrow { NotFoundException(ProductEntity.Companion::class.java) }
            }
            .toList()
    }
}