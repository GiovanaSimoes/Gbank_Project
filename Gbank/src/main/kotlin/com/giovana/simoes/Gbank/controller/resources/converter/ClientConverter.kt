package com.giovana.simoes.Gbank.controller.resources.converter

import com.giovana.simoes.Gbank.controller.resources.dto.ClientDTO
import com.giovana.simoes.Gbank.entity.Client
import org.springframework.stereotype.Component

@Component
class ClientConverter {
    fun convert(clientDTO: ClientDTO): Client {
        return Client(
            email = clientDTO.email,
            name = clientDTO.name,
            cell = clientDTO.cell,
            cpf = clientDTO.cpf,
            id = clientDTO.id
        )
    }

    fun convert(client: Client): ClientDTO {
        return ClientDTO(
            email = client.email,
            name = client.name,
            cell = client.cell,
            cpf = client.cpf,
            id = client.id
        )
    }
}

