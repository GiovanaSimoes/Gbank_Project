package com.giovana.simoes.Gbank.controller

import com.giovana.simoes.Gbank.controller.resources.converter.BankAccountConverter
import com.giovana.simoes.Gbank.controller.resources.dto.*
import com.giovana.simoes.Gbank.service.BankAccountService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bank-account")
class BankAccountController(
    private val bankAccountService: BankAccountService,
    private val bankAccountConverter: BankAccountConverter
) {

    @GetMapping("/read/{id}")
    fun read (@PathVariable("id") id: Long): ResponseEntity<BankAccountDTO>{
        return ResponseEntity.ok(bankAccountService.read(id))
    }

    @PostMapping("/create")
    fun create(@RequestBody bankAccountRequest: BankAccountRequest): ResponseEntity<BankAccountDTO> {
        val bankAccountCreated = bankAccountService.create(bankAccountRequest)
        return ResponseEntity.ok(bankAccountConverter.convert(bankAccountCreated))
    }

    @PutMapping("/{id}/withdraw")
    fun withdrawalRequest(@PathVariable("id") id: Long, @RequestBody withdrawRequest: WithdrawRequest): ResponseEntity<BankAccountDTO> {
        val bankAccount = bankAccountService.withdraw(id,withdrawRequest)
        return ResponseEntity.ok(bankAccount)
    }
    @PutMapping("/{id}/deposit")
    fun depositRequest(@PathVariable("id") id: Long, @RequestBody  depositRequest: DepositRequest): ResponseEntity<BankAccountDTO> {
        val bankAccount = bankAccountService.deposit(id,depositRequest)
        return ResponseEntity.ok(bankAccount)
    }

    @PostMapping("/{id}/transfer")
    fun transferRequest(@PathVariable("id")id: Long,@RequestBody transferRequest: TransferRequest): ResponseEntity<BankAccountDTO>{
        val bankAccount = bankAccountService.transfer(id, transferRequest)
        return ResponseEntity.ok(bankAccount)
    }
}
