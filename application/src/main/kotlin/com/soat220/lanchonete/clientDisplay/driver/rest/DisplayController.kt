package com.soat220.lanchonete.clientDisplay.driver.rest

import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.erp.usecase.FindProducts
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/display"], produces = [MediaType.APPLICATION_JSON_VALUE])
class DisplayController(
    private val findProducts: FindProducts,
) {
    @GetMapping("/products")
    fun findProducts() = findProducts.execute().orThrow()
}