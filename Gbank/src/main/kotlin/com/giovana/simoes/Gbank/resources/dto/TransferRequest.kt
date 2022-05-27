package com.giovana.simoes.Gbank.resources.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class TransferRequest(
    @field:Positive
    @field:NotNull
    val amount: Double,
    @field:NotEmpty
    val idDestiny: Long
)
