package com.soat220.lanchonete.product.gateway

import com.soat220.lanchonete.exception.CreateProductException
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.product.gateway.postgresdb.ProductRepository
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success

import org.springframework.stereotype.Service
import com.soat220.lanchonete.product.gateway.postgresdb.model.Product as DatabaseProduct
import com.soat220.lanchonete.product.model.Product as DomainProduct

@Service
class CreateProductProvider(
    private val productRepository: ProductRepository
) : CreateProductGateway {
    override fun execute(product: DomainProduct): Result<DomainProduct, DomainException> {

        try {
            productRepository.save(DatabaseProduct.fromDomain(product))
        } catch (e: Exception) {
            return Failure(
                CreateProductException(
                    product.name,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }

        return Success(product)
    }
}