package com.giovana.simoes.Gbank.service

import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val clientRepository: ClientRepository
) {

    fun create(client: Client): Client {
        return clientRepository.save(client)
    }
}
