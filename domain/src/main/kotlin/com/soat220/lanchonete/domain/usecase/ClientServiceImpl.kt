package com.soat220.lanchonete.domain.usecase

import com.soat220.lanchonete.domain.model.Client
import com.soat220.lanchonete.domain.port.driven.ClientPersistenceInterface
import com.soat220.lanchonete.domain.port.driver.ClientServiceInterface
import com.soat220.lanchonete.exception.CreateClientException
import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.exception.ErrorCode
import com.soat220.lanchonete.result.Failure
import com.soat220.lanchonete.result.Result
import com.soat220.lanchonete.result.Success
import javax.inject.Named

@Named
class ClientServiceImpl(
    private val clientPersistenceInterface: ClientPersistenceInterface
): ClientServiceInterface {

    override fun save(client: Client): Result<Client, DomainException> {
        return try {
            Success(clientPersistenceInterface.save(client))
        } catch (e: Exception) {
            return createFailureException(client.name, e)
        }
    }

    override fun findByCpf(cpf: String): Result<Client, DomainException> {
        return try {
            Success(clientPersistenceInterface.findByCpf(cpf))
        } catch (e: Exception) {
            return createFailureException(cpf, e)
        }
    }

    private fun createFailureException(value: String, e: Exception):Failure<CreateClientException> {
        return Failure(
            CreateClientException(
                value,
                listOf(DomainException(e, ErrorCode.DATABASE_ERROR))
            )
        )
    }

}