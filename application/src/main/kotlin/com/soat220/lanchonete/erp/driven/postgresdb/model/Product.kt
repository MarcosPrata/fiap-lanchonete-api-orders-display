package com.soat220.lanchonete.erp.driven.postgresdb.model

import com.soat220.lanchonete.common.model.Category
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    private val name: String,
    private val price: Double,
    private val category: Category
) {
    fun toDomain() = DomainProduct(
        id = id,
        name = name,
        price = price,
        category = category
    )

    companion object {
        fun fromDomain(product: DomainProduct) = Product(
            product.id,
            product.name,
            product.price,
            product.category
        )
    }
}