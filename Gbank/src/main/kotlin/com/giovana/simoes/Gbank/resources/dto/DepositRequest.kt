package com.giovana.simoes.Gbank.resources.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class DepositRequest(

    @field:NotNull
    @field:Positive
    val amount: Double

) {
}