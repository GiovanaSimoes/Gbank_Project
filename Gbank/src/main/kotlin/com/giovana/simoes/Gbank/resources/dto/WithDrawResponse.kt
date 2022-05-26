package com.giovana.simoes.Gbank.resources.dto

data class WithDrawResponse(
    val id: Long? = null,
    val balance: Double,
    val notes: List<Notes>
) {
}
