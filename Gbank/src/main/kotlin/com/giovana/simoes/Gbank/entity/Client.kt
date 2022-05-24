package com.giovana.simoes.Gbank.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Client(
    @Id
    @GeneratedValue
    var id: Long?=null,
    val name: String,
    val cpf: Long,
    val email: String,
    val cell: Int
) {
    override fun toString(): String {
        return "Client(Id = $id, name ='$name', cpf = $cpf, email = '$email', cell = $cell)"
    }
}

