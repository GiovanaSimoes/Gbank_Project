package com.giovana.simoes.Gbank.repository

import com.giovana.simoes.Gbank.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository: JpaRepository<Client,Long> {
}