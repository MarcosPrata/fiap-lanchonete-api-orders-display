package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.common.driven.postgresdb.ProductRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Product
import com.soat220.lanchonete.erp.exception.UpdateProductException
import com.soat220.lanchonete.erp.port.UpdateProductPort
import org.springframework.stereotype.Service
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Service
class UpdateProductAdapter(
    private val productRepository: ProductRepository
) : UpdateProductPort {
    override fun execute(product: DomainProduct): Result<DomainProduct, DomainException> {
        try {
            productRepository.save(Product.fromDomain(product))
        } catch (e: Exception) {
            return Failure(
                UpdateProductException(
                    product.id,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }

        return Success(product)
    }
}