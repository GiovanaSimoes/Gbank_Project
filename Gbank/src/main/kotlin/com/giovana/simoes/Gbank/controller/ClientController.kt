package com.giovana.simoes.Gbank.controller

import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client")
class ClientController(
    private val clientService: ClientService
) {

    @PostMapping("/")
    fun create(@RequestBody client: Client): ResponseEntity<Client>{

        return ResponseEntity.ok(clientService.create(client))
    }

    @GetMapping
    fun test():String{

        return "Hello Word!"
    }
}