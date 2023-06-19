package com.soat220.lanchonete.product.usecase

import com.soat220.lanchonete.port.driven.DomainPersistenceInterface
import com.soat220.lanchonete.port.driver.DomainServiceInterface
import com.soat220.lanchonete.exception.CreateProductException
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.product.model.Product
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success

class ProductServiceImpl(
    private val productPersistenceInterface: DomainPersistenceInterface<Product>
) : DomainServiceInterface<Product> {
    override fun save(model: Product): Result<Product, DomainException> {
        try {
            productPersistenceInterface.save(model)
        } catch (e: Exception) {
            return Failure(
                CreateProductException(
                    model.name,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }

        return Success(model)
    }


}