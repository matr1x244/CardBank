package com.example.cardbank.domain.data.models.room

import com.example.cardbank.domain.data.models.base.Bank
import com.example.cardbank.domain.data.models.base.BankBIN

interface RepositoryRoom {

    suspend fun getAllHistory(): List<Bank>
}