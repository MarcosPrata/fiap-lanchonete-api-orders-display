package com.soat220.lanchonete.adapter.database.postgres

import com.soat220.lanchonete.adapter.database.postgres.model.ProductEntity
import com.soat220.lanchonete.adapter.database.postgres.repository.ProductRepository
import com.soat220.lanchonete.domain.model.Product
import com.soat220.lanchonete.domain.port.driven.ProductPersistenceInterface
import org.springframework.stereotype.Service

@Service
class ProductPersistenceImpl(
    private val productRepository: ProductRepository
) : ProductPersistenceInterface {

    override fun save(product: Product): Product {
        return productRepository.save(ProductEntity.fromDomain(product)).toDomain()
    }
}