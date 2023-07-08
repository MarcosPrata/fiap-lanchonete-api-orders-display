package com.soat220.lanchonete.product.adapter.postgresdb.model

import com.soat220.lanchonete.product.model.Product
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Table(name = "PRODUCT")
class ProductEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Double
) {
    fun toDomain() = Product(
        id,
        name,
        price
    )

    companion object {
        fun fromDomain(product: Product) = ProductEntity(
            null,
            product.name,
            product.price
        )
    }
}