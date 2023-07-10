package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.exception.NotFoundException
import com.soat220.lanchonete.common.model.Product
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.erp.exception.DeleteProductByIdException
import com.soat220.lanchonete.erp.port.DeleteProductByIdPort
import org.springframework.stereotype.Service

@Service
class DeleteProductByIdAdapter(
    private val productRepository: ProductRepository
) : DeleteProductByIdPort {
    override fun execute(productId: Long): Result<Unit, DomainException> {
        return try {
            val product = productRepository.findById(productId).orElseThrow { NotFoundException(Product::class.java) }
            product.deleted = true
            productRepository.save(product)

            Success(Unit)
        } catch (e: Exception) {
            Failure(
                DeleteProductByIdException(
                    productId,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }
    }
}