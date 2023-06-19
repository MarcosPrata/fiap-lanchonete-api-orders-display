package com.soat220.lanchonete.port.driven

interface DomainPersistenceInterface<Domain> {

    fun save(domain: Domain): Domain

}