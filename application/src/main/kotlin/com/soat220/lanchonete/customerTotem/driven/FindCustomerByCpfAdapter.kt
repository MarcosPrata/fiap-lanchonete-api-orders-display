package com.soat220.lanchonete.customerTotem.driven

import com.soat220.lanchonete.common.driven.postgresdb.CustomerRepository
import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.exception.ErrorCode
import com.soat220.lanchonete.common.model.Customer
import com.soat220.lanchonete.common.result.Failure
import com.soat220.lanchonete.common.result.Result
import com.soat220.lanchonete.common.result.Success
import com.soat220.lanchonete.customerTotem.port.FindCustomerByCpfPort
import java.util.Objects.nonNull
import org.springframework.stereotype.Service

@Service
class FindCustomerByCpfAdapter(
    private val customerRepository: CustomerRepository
) : FindCustomerByCpfPort {
    override fun execute(cpf: String?): Result<Customer?, DomainException> {
        try {
            if (nonNull(cpf)) {
                return Success(customerRepository.findByCpf(cpf).toDomain())
            }
            return Success(null)
        } catch (e: Exception) {
            return Failure(
                DomainException(e, ErrorCode.DATABASE_ERROR)
            )
        }
    }
}