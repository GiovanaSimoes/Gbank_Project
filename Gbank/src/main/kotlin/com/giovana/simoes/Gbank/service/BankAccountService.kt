package com.giovana.simoes.Gbank.service

import com.giovana.simoes.Gbank.controller.resources.converter.BankAccountConverter
import com.giovana.simoes.Gbank.controller.resources.dto.BankAccountDTO
import com.giovana.simoes.Gbank.controller.resources.dto.BankAccountRequest
import com.giovana.simoes.Gbank.entity.BankAccount
import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.repository.BankAccountRepository
import com.giovana.simoes.Gbank.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class BankAccountService(
    private val bankAccountRepository: BankAccountRepository,
    private val clientRepository: ClientRepository,
    private val bankAccountConverter: BankAccountConverter,
) {
    fun create(bankAccountRequest: BankAccountRequest): BankAccount {
        val client = findClientById(bankAccountRequest.idOwner)
        val clientConverted = bankAccountConverter.convert(client, bankAccountRequest.balance)
        return bankAccountRepository.save(clientConverted)
    }

    fun read (id : Long): BankAccountDTO {
        val bankAccountRead = bankAccountRepository.getById(id)
        return bankAccountConverter.convert(bankAccountRead)
    }

    fun findClientById(id: Long): Client {
        return clientRepository.findById(id).orElse(null)
    }

//    fun withdraw(withdrawalValue: Double): Boolean {
//        if (bankAccount.balance >= withdrawalValue) {
//            bankAccount.balance  -= withdrawalValue
//            bankAccountRepository.saveAndFlush(bankAccount)
//        } else {
//            return false
//        }
//        return true
//    }
//    fun withdrawalRequestMessage(accountOrigin: BankAccount){
//        println("Qual valor você gostaria de sacar? ")
//        val withdrawalValue = readLine()!!.toDouble()
//        withdrawalRequest(accountOrigin,withdrawalValue)
//    }
//    fun withdrawalRequest(accountOrigin: BankAccount, withdrawalValue : Double) {
//        val resultWithdrawal = withdraw(withdrawalValue)
//        //withdrawalMessage(resultWithdrawal, withdrawalValue, accountOrigin.balance)
//    }
//
//    fun withdrawalMessage(status: Boolean, withdrawalValue: Double, balance: Double) {
//        if (status) {
//            println("${GREEN}Saque efetuado com sucesso!!$RESET\nSeu saldo atual é: R$$balance\n")
//            cashMachine(withdrawalValue.toInt())
//        } else {
//            println("${RED}Saldo Insuficiente, operação não efetuada!$RESET")
//        }
//    }
}
