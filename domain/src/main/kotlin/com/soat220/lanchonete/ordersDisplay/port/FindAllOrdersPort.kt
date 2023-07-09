package com.soat220.lanchonete.ordersDisplay.port

import com.soat220.lanchonete.common.exception.DomainException
import com.soat220.lanchonete.common.model.Order
import com.soat220.lanchonete.common.result.Result

interface FindAllOrdersPort {

    fun execute(): Result<List<Order>, DomainException>
}