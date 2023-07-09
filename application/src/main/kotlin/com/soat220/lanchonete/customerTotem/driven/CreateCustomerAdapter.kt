package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.driven.postgresdb.model.Customer
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.exception.CreateCustomerException
import com.soat220.lanchonete.customerTotem.port.CreateCustomerPort
import org.springframework.stereotype.Service

import com.soat220.lanchonete.common.model.Customer as DomainCustomer

@Service
class CreateCustomerAdapter(
    private val customerRepository: CustomerRepository
) : CreateCustomerPort {

    override fun execute(customer: DomainCustomer): Result<DomainCustomer, DomainException> {
        return try {
            Success(customerRepository.save(Customer.fromDomain(customer)).toDomain())
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
