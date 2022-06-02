package com.giovana.simoes.Gbank.service

import com.giovana.simoes.Gbank.entity.BankAccount
import com.giovana.simoes.Gbank.entity.Client
import com.giovana.simoes.Gbank.repository.BankAccountRepository
import com.giovana.simoes.Gbank.repository.ClientRepository
import com.giovana.simoes.Gbank.resources.converter.BankAccountConverter
import com.giovana.simoes.Gbank.resources.dto.*
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class BankAccountServiceTest{
    @MockK
    lateinit var bankAccountRepository: BankAccountRepository
    @MockK
    lateinit var clientRepository: ClientRepository
    @MockK
    lateinit var bankAccountConverter: BankAccountConverter
    @InjectMockKs
    lateinit var bankAccountService: BankAccountService

    @Test
    fun `given an account with $1000 when requesting the withdrawal of $100 should withdraw this amount from that account `(){
        val id = 1L
        val withDrawResponse = mockk<WithDrawResponse>()
        val withdrawRequest = WithdrawRequest(
        amount = 100.0
        )
        val bankAccount = BankAccount(
            id = id,
            owner = Client(
                id = 1L,
                name = "Giovana",
                cpf = 58668793445,
                email = "giovana@gmail.com",
                cell = 59834575
            ),
        balance = 1000.0
        )
        every { bankAccountRepository.findById(id)}.returns(Optional.of(bankAccount))
        every{ bankAccountRepository.saveAndFlush(any())}.returns(bankAccount)
        every{ bankAccountConverter.convertWithDraw(any(),any())}.returns(withDrawResponse)

        val response = bankAccountService.withdraw(id,withdrawRequest)

        Assertions.assertEquals(900.0, response.balance)
    }
    @Test
    fun `given a account with $1000 when requesting the deposit of $150 should add to account value`(){
        val id = 1L
        val depositRequest = DepositRequest(
            amount = 150.0
        )
        val bankAccount = BankAccount(
            id = id,
            owner = Client(
                id = 1,
                name = "Deysi",
                cpf = 58668793445,
                email = "deysi@gmail.com",
                cell = 59834575
            ),
            balance = 1000.0
        )
        every { bankAccountRepository.findById(id)}.returns(Optional.of(bankAccount))
        every{ bankAccountRepository.saveAndFlush(any())}.returns(bankAccount)

        val response = bankAccountService.deposit(id,depositRequest)

        Assertions.assertEquals(1150.0,response.balance)
    }

    @Test
    fun `given a account origin with $1000 when requesting the transfer of $200 should add to account value destiny and withdraw source account`(){
        val idOrigin = 1L
        val transferRequest = TransferRequest(
            amount = 200.0,
            idDestiny = 2
        )
        val bankAccountOrigin = BankAccount(
            id = idOrigin,
            owner = Client(
                id = 1,
                name = "Deysi",
                cpf = 58668793445,
                email = "deysi@gmail.com",
                cell = 59834575
            ),
            balance = 1000.0
        )
        val bankAccountDestiny = BankAccount(
            id = idOrigin,
            owner = Client(
                id = 1,
                name = "Giovana",
                cpf = 58668793445,
                email = "giovana@gmail.com",
                cell = 59834575
            ),
            balance = 1000.0
        )

        every { bankAccountRepository.findById(idOrigin)}.returns(Optional.of(bankAccountOrigin))
        every { bankAccountRepository.findById(transferRequest.idDestiny)}.returns(Optional.of(bankAccountDestiny))
        every { bankAccountRepository.saveAndFlush(any())}.returns(bankAccountOrigin)
        every { bankAccountRepository.saveAndFlush(any())}.returns(bankAccountDestiny)

        val response = bankAccountService.transfer(idOrigin,transferRequest)

        Assertions.assertEquals(800.0,response.balance)
        Assertions.assertEquals(1200.0,bankAccountDestiny.balance)
    }
}
