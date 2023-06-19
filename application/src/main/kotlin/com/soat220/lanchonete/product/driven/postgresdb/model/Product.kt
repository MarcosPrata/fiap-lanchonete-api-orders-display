package com.soat220.lanchonete.product.driven.postgresdb.model

import com.soat220.lanchonete.product.model.Category
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import com.soat220.lanchonete.product.model.Product as DomainProduct

@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Double,
    val category: Category
) {
    fun toDomain() = DomainProduct(
        name = name,
        price = price,
        category = category,
        imageUrls = listOf()
    )

    companion object {
        fun fromDomain(product: DomainProduct) = Product(
            null,
            product.name,
            product.price,
            product.category
        )
    }
}