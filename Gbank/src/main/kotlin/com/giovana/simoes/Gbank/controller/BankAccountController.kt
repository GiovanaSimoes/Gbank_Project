package com.giovana.simoes.Gbank.controller

import com.giovana.simoes.Gbank.resources.converter.BankAccountConverter
import com.giovana.simoes.Gbank.resources.dto.*
import com.giovana.simoes.Gbank.service.BankAccountService
import org.springframework.http.ResponseEntity
import javax.validation.Valid
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
    fun withdrawalRequest(@PathVariable("id") id: Long,@RequestBody withdrawRequest: WithdrawRequest): ResponseEntity<WithDrawResponse> {
        return ResponseEntity.ok(bankAccountService.withdraw(id,withdrawRequest))
    }
    @PutMapping("/{id}/deposit")
    fun depositRequest(@PathVariable("id") id: Long,@Valid @RequestBody  depositRequest: DepositRequest): ResponseEntity<OperationsResponse> {
        val bankAccount = bankAccountService.deposit(id,depositRequest)
        return ResponseEntity.ok(bankAccount)
    }

    @PostMapping("/{id}/transfer")
    fun transferRequest(@PathVariable("id")id: Long,@RequestBody transferRequest: TransferRequest): ResponseEntity<OperationsResponse>{
        val bankAccount = bankAccountService.transfer(id, transferRequest)
        return ResponseEntity.ok(bankAccount)
    }
}
