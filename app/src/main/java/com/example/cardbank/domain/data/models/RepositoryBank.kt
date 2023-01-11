package com.example.cardbank.domain.data.models

import com.example.cardbank.domain.data.models.base.Bank
import com.example.cardbank.domain.data.models.base.BankBIN

interface RepositoryBank {

    suspend fun observerBin(bin: String): BankBIN

    suspend fun saveBin(bank: Bank)
}