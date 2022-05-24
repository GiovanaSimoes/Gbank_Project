package com.giovana.simoes.Gbank.controller

import com.giovana.simoes.Gbank.controller.resources.converter.BankAccountConverter
import com.giovana.simoes.Gbank.controller.resources.dto.BankAccountDTO
import com.giovana.simoes.Gbank.controller.resources.dto.BankAccountRequest
import com.giovana.simoes.Gbank.controller.resources.dto.ClientDTO
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
    fun create(@RequestBody @Validated bankAccountRequest: BankAccountRequest): ResponseEntity<BankAccountDTO> {
        val bankAccountCreated = bankAccountService.create(bankAccountRequest)
        return ResponseEntity.ok(bankAccountConverter.convert(bankAccountCreated))
    }

    @PutMapping("/{id}/withdraw")
    fun withdrawalRequest(){

    }
}
