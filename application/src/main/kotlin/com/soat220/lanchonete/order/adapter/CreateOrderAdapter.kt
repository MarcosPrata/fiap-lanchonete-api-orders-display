package com.soat220.lanchonete.order.adapter

import com.soat220.lanchonete.customer.adapter.postgresdb.model.CustomerEntity
import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.order.adapter.postgresdb.model.OrderEntity
import com.soat220.lanchonete.order.adapter.postgresdb.OrderRepository
import com.soat220.lanchonete.order.model.Order
import com.soat220.lanchonete.order.port.CreateOrderPort
import com.soat220.lanchonete.customer.port.FindCustomerByCpfPort
import com.soat220.lanchonete.exception.*
import com.soat220.lanchonete.product.adapter.postgresdb.ProductRepository
import com.soat220.lanchonete.product.adapter.postgresdb.model.ProductEntity
import com.soat220.lanchonete.result.*
import org.springframework.stereotype.Service
import java.util.Objects.nonNull

@Service
class CreateOrderAdapter(
    private val orderRepository: OrderRepository,
    private val findCustomerByCpfPort: FindCustomerByCpfPort,
    private val productRepository: ProductRepository
) : CreateOrderPort {

    override fun execute(customerCpf: String?, productsIds: List<Long>, notes: String): Result<Order, DomainException> {

        val products = retriveProducts(productsIds)
        var customerEntity: Customer? = null

        if (nonNull(customerCpf)) {
            customerEntity = findCustomerByCpfPort.execute(customerCpf).getOrNull()
        }

        val orderEntity = OrderEntity.from(customerEntity, products.toMutableList(), notes)

        return try {
            Success(orderRepository.save(orderEntity).toDomain())
        } catch (e: Exception) {
            return Failure(
                CreateOrderException(
                    null,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
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