package com.soat220.lanchonete.erp.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.erp.port.FindAllCustomersPort
import org.springframework.stereotype.Service

@Service
class FindAllCustomersAdapter(
    private val customerRepository: CustomerRepository
) : FindAllCustomersPort {

    override fun execute(): Result<List<Customer>, DomainException> {
        return try {
            Success(customerRepository.findAll().map { it.toDomain() })
        } catch (e: Exception) {
            Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }
}