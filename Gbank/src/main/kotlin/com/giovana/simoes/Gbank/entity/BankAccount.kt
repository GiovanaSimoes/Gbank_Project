package com.giovana.simoes.Gbank.entity

import javax.persistence.*

@Entity
class BankAccount(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "client_id")
    var owner: Client,
    var balance: Double = 0.0,
) {

    override fun toString(): String {
        return "BankAccount(accountId = $id, name = ${owner.name} balance = R$$balance)"
    }
}
//Withdraw - Put/Path - Irá atualizar o saldo do cliente retirando o valor sacado
//Transfer -  Post - Irá atualizar o saldo do cliente retirando o valor transferido e atualizar o saldo do cliente que recebeu a transferencia adicinando o valor
//Deposit -  Put/Path - Irá atualizar o saldo do cliente acrescentando o valor de deposito

//Get - Trás valores, read
//Post - Cria um recurso
//Put - Atualizar
//Delete - deletar