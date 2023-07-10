package com.soat220.lanchonete.common.driven.postgresdb.model

import com.soat220.lanchonete.common.model.enums.Category
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import com.soat220.lanchonete.common.model.Product as DomainProduct

@Entity
@Table(name = "product")
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    private val name: String,
    private val description: String,
    private val category: Category,
    private val price: Double,
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val images: MutableList<ProductImage>,
    var deleted: Boolean
) {
    fun toDomain() = DomainProduct(
        id = id,
        name = name,
        description = description,
        category = category,
        price = price,
        imageUrls = images.map { it.imageUrl },
        deleted = deleted
    )

    companion object {
        fun fromDomain(product: DomainProduct) = Product(
            id = product.id,
            name = product.name,
            description = product.description,
            category = product.category,
            price = product.price,
            images = mutableListOf(),
            deleted = product.deleted
        )
    }
}