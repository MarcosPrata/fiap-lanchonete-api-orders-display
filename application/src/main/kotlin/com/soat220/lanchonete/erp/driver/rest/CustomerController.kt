package com.soat220.lanchonete.erp.driver.rest

import com.soat220.lanchonete.common.result.orThrow
import com.soat220.lanchonete.erp.usecase.FindAllCustomers
import com.soat220.lanchonete.erp.usecase.FindCustomerById
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/erp/customers"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CustomerController(
    private val findCustomers: FindAllCustomers,
    private val findCustomerById: FindCustomerById,
) {
    @GetMapping
    fun findCustomers() = findCustomers.execute().orThrow()

    @GetMapping("/{customerId}")
    fun findCustomerById(@PathVariable customerId: Long) = findCustomerById.execute(customerId).orThrow()
}