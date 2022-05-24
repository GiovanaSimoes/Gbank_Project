package com.giovana.simoes.Gbank.service

import com.giovana.simoes.Gbank.controller.resources.converter.BankAccountConverter
import com.giovana.simoes.Gbank.controller.resources.dto.*
import com.giovana.simoes.Gbank.entity.BankAccount
import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.repository.BankAccountRepository
import com.giovana.simoes.Gbank.repository.ClientRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    fun read(id: Long): BankAccountDTO {
        val bankAccountRead = bankAccountRepository.getById(id)
        return bankAccountConverter.convert(bankAccountRead)
    }

    fun findClientById(id: Long): Client {
        return clientRepository.findById(id).orElse(null)
    }

    fun withdraw(id: Long, withdrawRequest: WithdrawRequest): BankAccountDTO {
        val findAccount = bankAccountRepository.findById(id).get()
        if (haveBalance(findAccount, withdrawRequest.amount) ){
            findAccount.balance -= withdrawRequest.amount
            bankAccountRepository.saveAndFlush(findAccount)
            //return é correto ser aqui
        }
        //Aqui o certo seria lançar uma exceção
        return bankAccountConverter.convert(findAccount)
    }
    private fun haveBalance(bankAccount: BankAccount, amount: Double): Boolean = bankAccount.balance >= amount

    fun deposit (id: Long, depositRequest: DepositRequest): BankAccountDTO{
        val findAccount = bankAccountRepository.findById(id).get()
        findAccount.balance += depositRequest.amount
        bankAccountRepository.saveAndFlush(findAccount)
        return bankAccountConverter.convert(findAccount)
    }

    @Transactional
    fun transfer(idOrigin: Long,transferRequest: TransferRequest): BankAccountDTO{
        val findAccountOrigin = bankAccountRepository.findById(idOrigin).get()
        val findAccountDestiny = bankAccountRepository.findById(transferRequest.idDestiny).get()

        if(haveBalance(findAccountOrigin,transferRequest.amount)){
            findAccountOrigin.balance -= transferRequest.amount
            findAccountDestiny.balance += transferRequest.amount
            bankAccountRepository.saveAndFlush(findAccountDestiny)
            bankAccountRepository.saveAndFlush(findAccountOrigin)
        }
            return bankAccountConverter.convert(findAccountOrigin)
    }
}
