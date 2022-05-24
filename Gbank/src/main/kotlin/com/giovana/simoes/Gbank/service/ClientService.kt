package com.giovana.simoes.Gbank.service

import com.giovana.simoes.Gbank.controller.resources.converter.ClientConverter
import com.giovana.simoes.Gbank.controller.resources.dto.ClientDTO
import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val clientConverter: ClientConverter
) {
    fun create(client: Client): Client {
        return clientRepository.save(client)
    }
    fun read (id : Long): ClientDTO {
        val clientRead = clientRepository.getById(id)
        return clientConverter.convert(clientRead)
    }
    fun update (id: Long, client: Client): ClientDTO {
        client.id = id
       return clientConverter.convert(clientRepository.saveAndFlush(client))
    }
    fun delete(id: Long){
        return clientRepository.deleteById(id)
    }
}
