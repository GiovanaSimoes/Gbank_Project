package com.giovana.simoes.Gbank.controller.resources.dto

data class TransferRequest(


    val amount: Double,
    val idDestiny: Long
) {
}
//Colocar validação 0 transferencia >0
