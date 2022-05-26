package com.giovana.simoes.Gbank.resources.dto

data class BankAccountRequest(
    val idOwner: Long,
    val balance: Double
) {
}
