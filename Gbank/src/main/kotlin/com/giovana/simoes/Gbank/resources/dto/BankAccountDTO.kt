package com.giovana.simoes.Gbank.resources.dto

import com.giovana.simoes.Gbank.entity.Client

data class BankAccountDTO(
    val id: Long? = null,
    val owner: Client,
    val balance: Double
) {
}
