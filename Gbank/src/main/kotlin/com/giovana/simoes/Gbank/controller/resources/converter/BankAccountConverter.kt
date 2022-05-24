package com.giovana.simoes.Gbank.controller.resources.converter

import com.giovana.simoes.Gbank.controller.resources.dto.BankAccountDTO
import com.giovana.simoes.Gbank.entity.BankAccount
import com.giovana.simoes.Gbank.entity.Client
import org.springframework.stereotype.Component

@Component
class BankAccountConverter {

    fun convert(bankAccountDTO: BankAccountDTO): BankAccount {
        return BankAccount(
            owner = bankAccountDTO.owner,
            balance = bankAccountDTO.balance,
            id = bankAccountDTO.id

        )
    }

    fun convert(bankAccount: BankAccount): BankAccountDTO {
        return BankAccountDTO(
            owner = bankAccount.owner,
            balance = bankAccount.balance,
            id = bankAccount.id
        )
    }

    fun convert(client: Client, balance: Double): BankAccount {
        return BankAccount(
            owner = client,
            balance = balance
        )

    }
}
