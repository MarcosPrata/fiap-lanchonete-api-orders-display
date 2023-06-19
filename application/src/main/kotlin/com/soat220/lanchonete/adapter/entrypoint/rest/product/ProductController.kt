package com.soat220.lanchonete.adapter.entrypoint.rest.product

import com.soat220.lanchonete.adapter.entrypoint.rest.product.dto.CreateProductRequest
import com.soat220.lanchonete.domain.port.driver.ProductServiceInterface
import com.soat220.lanchonete.result.orThrow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(
    private val productService: ProductServiceInterface
) {
    @PostMapping
    fun createProduct(@RequestBody createProductRequest: CreateProductRequest) =
        productService.save(createProductRequest.toDomain()).orThrow()
}