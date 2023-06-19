package com.soat220.lanchonete.domain.usecase

import com.soat220.lanchonete.domain.model.Product
import com.soat220.lanchonete.domain.port.driven.ProductPersistenceInterface
import com.soat220.lanchonete.domain.port.driver.ProductServiceInterface
import com.soat220.lanchonete.exception.CreateProductException
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import javax.inject.Named

@Named
class ProductServiceImpl(
    private val productPersistenceInterface: ProductPersistenceInterface
) : ProductServiceInterface {

    override fun save(product: Product): Result<Product, DomainException> {
        try {
            productPersistenceInterface.save(product)
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