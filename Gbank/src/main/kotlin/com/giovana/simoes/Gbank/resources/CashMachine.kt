package com.giovana.simoes.Gbank.resources.dto


object  CashMachine{
    var amount: Int = 0

    fun getNotes(totalValue: Int): List<Notes>{
        amount = totalValue

        return listOf(getRemainingAmount(Note.ONEHUNDRED), getRemainingAmount(Note.FIFTY),
            getRemainingAmount(Note.TEN), getRemainingAmount(Note.FIVE), getRemainingAmount(Note.ONE))
    }

    private fun getRemainingAmount(note: Note): Notes {
        val qtdNotes = (amount / note.weight)
        amount -= (qtdNotes * note.weight)
        return Notes(note,qtdNotes)
    }

}
data class Notes(
    val note: Note,
    val quantity: Int
)

enum class Note(var weight: Int) {
    ONE(1),
    FIVE(5),
    TEN(10),
    FIFTY(50),
    ONEHUNDRED(100)
}
