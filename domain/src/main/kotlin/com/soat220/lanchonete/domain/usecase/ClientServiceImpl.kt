package com.soat220.lanchonete.domain.usecase

import com.soat220.lanchonete.domain.model.Client
import com.soat220.lanchonete.port.driven.DomainPersistenceInterface
import com.soat220.lanchonete.port.driver.DomainServiceInterface
import com.soat220.lanchonete.exception.CreateProductException
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import javax.inject.Named

@Named
class ClientServiceImpl(

    private val clientPersistenceInterface: DomainPersistenceInterface<Client>

): DomainServiceInterface<Client> {

    override fun save(model: Client): Result<Client, DomainException> {
        try {
            clientPersistenceInterface.save(model)
        } catch (e: Exception) {
            return Failure(
                CreateProductException(
                    model.name,
                    listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
                )
            )
        }

        return Success(model)
    }

}