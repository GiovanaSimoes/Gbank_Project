package com.giovana.simoes.Gbank.repository

import com.giovana.simoes.Gbank.entity.BankAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankAccountRepository : JpaRepository<BankAccount, Long> {
}
