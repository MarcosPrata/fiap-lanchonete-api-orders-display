package com.soat220.lanchonete.customer.adapter

import com.soat220.lanchonete.customer.adapter.postgresdb.model.CustomerEntity
import com.soat220.lanchonete.customer.adapter.postgresdb.CustomerRepository
import com.soat220.lanchonete.customer.model.Customer
import com.soat220.lanchonete.customer.port.CreateCustomerPort
import com.soat220.lanchonete.exception.CreateCustomerException
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import org.springframework.stereotype.Service

@Service
class CreateCustomerAdapter(
    private val customerRepository: CustomerRepository
) : CreateCustomerPort {

    override fun execute(customer: Customer): Result<Customer, DomainException> {
        return try {
            Success(customerRepository.save(CustomerEntity.fromDomain(customer)).toDomain())
        } catch (e: Exception) {
            return Failure(
                CreateCustomerException(
                    customer.name,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }
    }
}
