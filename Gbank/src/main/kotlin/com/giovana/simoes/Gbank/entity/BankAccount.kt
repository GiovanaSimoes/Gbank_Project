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
