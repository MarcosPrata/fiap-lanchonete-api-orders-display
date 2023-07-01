package com.soat220.lanchonete.erp.driver.rest

import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.erp.usecase.CreateProduct
import com.soat220.lanchonete.erp.usecase.DeleteProduct
import com.soat220.lanchonete.erp.usecase.FindProductById
import com.soat220.lanchonete.erp.usecase.FindProducts
import com.soat220.lanchonete.erp.usecase.UpdateProduct
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.soat220.lanchonete.erp.driver.rest.dto.request.CreateProduct as CreateProductRequest
import com.soat220.lanchonete.erp.driver.rest.dto.request.UpdateProduct as UpdateProductRequest

@RestController
@RequestMapping(value = ["/api/erp/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(
    private val createProduct: CreateProduct,
    private val findProducts: FindProducts,
    private val findProductById: FindProductById,
    private val updateProduct: UpdateProduct,
    private val deleteProduct: DeleteProduct,
) {
    @PostMapping
    fun createProduct(@RequestBody createProduct: CreateProductRequest) =
        this.createProduct.execute(createProduct.toDomain()).orThrow()

    @GetMapping
    fun findProducts() = findProducts.execute().orThrow()

    @GetMapping("/{productId}")
    fun findProductById(@PathVariable productId: Long) = findProductById.execute(productId).orThrow()

    @PutMapping("/{productId}")
    fun updateProduct(
        @PathVariable productId: Long,
        @RequestBody updateProduct: UpdateProductRequest
    ) = this.updateProduct.execute(updateProduct.toDomain(productId)).orThrow()

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long) = deleteProduct.execute(productId).orThrow()
}