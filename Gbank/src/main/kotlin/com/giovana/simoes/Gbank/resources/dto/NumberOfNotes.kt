package com.giovana.simoes.Gbank.resources.dto

import com.giovana.simoes.Gbank.entity.Client

data class NumberOfNotes(
    val notesOneHundred: Int,
    val notesFifty: Int,
    val notesTen: Int,
    val notesFive: Int,
    val notesOne: Int
) {
}
