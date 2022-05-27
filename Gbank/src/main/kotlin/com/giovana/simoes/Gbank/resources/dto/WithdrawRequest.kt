package com.giovana.simoes.Gbank.resources.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class WithdrawRequest(
    @field:Positive
    @field:NotNull
    val amount: Double
)
