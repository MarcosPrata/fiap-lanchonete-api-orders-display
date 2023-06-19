package com.soat220.lanchonete.adapter.database.postgres

import com.soat220.lanchonete.adapter.database.postgres.model.ProductEntity
import com.soat220.lanchonete.adapter.database.postgres.repository.ProductRepository
import com.soat220.lanchonete.port.driven.DomainPersistenceInterface
import com.soat220.lanchonete.product.model.Product
import org.springframework.stereotype.Service

@Service
class ProductPersistenceImpl(
    private val productRepository: ProductRepository
) : DomainPersistenceInterface<Product> {

    override fun save(domain: Product): Product {
        return productRepository.save(ProductEntity.fromDomain(domain)).toDomain()
    }
}