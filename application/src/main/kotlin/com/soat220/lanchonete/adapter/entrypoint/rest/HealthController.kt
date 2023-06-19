package com.soat220.lanchonete.adapter.entrypoint.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["api/health"])
class HealthController {

    @GetMapping
    fun health(): String {
        return "UP"
    }
}