package com.giovana.simoes.Gbank.controller.resources.dto

data class BankAccountRequest(
    val idOwner: Long,
    val balance: Double
) {
}
