package com.giovana.simoes.Gbank.resources.dto

import javax.validation.constraints.NotEmpty

data class BankAccountRequest(
    @field:NotEmpty
    val idOwner: Long,
    val balance: Double
)
