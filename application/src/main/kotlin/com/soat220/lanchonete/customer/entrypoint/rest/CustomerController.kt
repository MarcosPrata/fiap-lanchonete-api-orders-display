package com.soat220.lanchonete.rest

import com.soat220.lanchonete.rest.customer.dto.CreateCustomerRequest
import com.soat220.lanchonete.customer.usecase.CreateCustomer
import com.soat220.lanchonete.customer.usecase.FindCustomerByCpf
import com.soat220.lanchonete.result.orThrow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/customers"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CustomerController(
    private val createCustomer: CreateCustomer,
    private val findCustomerByCpf: FindCustomerByCpf
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody createCustomerRequest: CreateCustomerRequest) =
        createCustomer.execute(createCustomerRequest.toModel()).orThrow()

    @GetMapping(value = ["/{cpf}"])
    @ResponseStatus(HttpStatus.OK)
    fun findByCpf(@PathVariable cpf: String) =
        findCustomerByCpf.execute(cpf).orThrow()

}