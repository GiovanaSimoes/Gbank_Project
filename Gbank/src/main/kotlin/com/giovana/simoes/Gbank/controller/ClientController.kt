package com.giovana.simoes.Gbank.controller

import com.giovana.simoes.Gbank.controller.resources.ClientConverter
import com.giovana.simoes.Gbank.controller.resources.ClientDTO
import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client")
class ClientController(
    private val clientService: ClientService,
    private val clientConverter: ClientConverter
    ) {
    @PostMapping("/")
    fun create(@RequestBody @Validated client: ClientDTO): ResponseEntity<ClientDTO>{
        val clientCreated = clientService.create(clientConverter.convert(client))

        return ResponseEntity.ok(clientConverter.convert(clientCreated))
    }
}
