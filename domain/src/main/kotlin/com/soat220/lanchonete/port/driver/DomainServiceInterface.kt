package com.soat220.lanchonete.port.driver

import com.soat220.lanchonete.exception.DomainException
import com.soat220.lanchonete.result.Result

interface DomainServiceInterface <Domain> {

    fun save (model: Domain): Result<Domain, DomainException>

}