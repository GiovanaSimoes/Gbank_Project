package com.giovana.simoes.Gbank.controller

import com.giovana.simoes.Gbank.controller.resources.converter.ClientConverter
import com.giovana.simoes.Gbank.controller.resources.dto.ClientDTO
import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
class ClientController(
    private val clientService: ClientService,
    private val clientConverter: ClientConverter
) {
    @GetMapping("/read/{id}")
    fun read (@PathVariable("id") id: Long): ResponseEntity<ClientDTO>{
        return ResponseEntity.ok(clientService.read(id))
    }
    @PostMapping("/create")
    fun create(@RequestBody  client: ClientDTO): ResponseEntity<ClientDTO> {
        val clientCreated = clientService.create(clientConverter.convert(client))

        return ResponseEntity.ok(clientConverter.convert(clientCreated))
    }

    @PutMapping("/update/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody client: Client): ResponseEntity<ClientDTO>{
        return ResponseEntity.ok(clientService.update(id, client))
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")id: Long){
        clientService.delete(id)
    }
}
//Tirar os path da URL
