package com.soat220.lanchonete.product.entrypoint.rest

import com.soat220.lanchonete.product.entrypoint.rest.dto.CreateProductRequest
import com.soat220.lanchonete.product.usecase.CreateProduct
import com.soat220.lanchonete.result.orThrow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(
    private val createProduct: CreateProduct,
) {
    @PostMapping
    fun createProduct(@RequestBody createProductRequest: CreateProductRequest) =
        createProduct.execute(createProductRequest.toDomain()).orThrow()
}
