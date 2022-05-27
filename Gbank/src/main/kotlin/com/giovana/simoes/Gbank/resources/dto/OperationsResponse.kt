package com.giovana.simoes.Gbank.resources.dto

import javax.validation.constraints.NotNull

data class OperationsResponse(
    val id: Long? = null,
    val balance: Double,
){
}