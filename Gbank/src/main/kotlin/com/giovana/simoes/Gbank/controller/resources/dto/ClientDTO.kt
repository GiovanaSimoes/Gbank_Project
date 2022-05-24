package com.giovana.simoes.Gbank.controller.resources.dto

data class ClientDTO(
    val id: Long? = null,
    val name: String,
    val cpf: Long,
    val email: String,
    val cell: Int
) {
}
