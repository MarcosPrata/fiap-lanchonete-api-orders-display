package com.soat220.lanchonete.product.adapter

import com.soat220.lanchonete.exception.CreateProductException
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.product.adapter.postgresdb.ProductRepository
import com.soat220.lanchonete.product.adapter.postgresdb.model.ProductEntity
import com.soat220.lanchonete.product.model.Product
import com.soat220.lanchonete.product.port.CreateProductPort
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import org.springframework.stereotype.Service

@Service
class CreateProductAdapter(
    private val productRepository: ProductRepository
) : CreateProductPort {
    override fun execute(product: Product): Result<Product, DomainException> {

        return try {
            Success(productRepository.save(ProductEntity.fromDomain(product)).toDomain())
        } catch (e: Exception) {
            return Failure(
                CreateProductException(
                    product.name,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }
    }
}