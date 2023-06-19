package com.soat220.lanchonete.adapter.database.postgres.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import com.soat220.lanchonete.domain.model.Product as DomainProduct

@Entity
class ProductEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Double
) {
    fun toDomain() = DomainProduct(
        name,
        price
    )

    companion object {
        fun fromDomain(product: DomainProduct) = ProductEntity(
            null,
            product.name,
            product.price
        )
    }
}