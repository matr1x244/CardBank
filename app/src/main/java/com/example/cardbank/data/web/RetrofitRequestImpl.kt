package com.example.cardbank.data.web

import com.example.cardbank.domain.data.models.RepositoryBank
import com.example.cardbank.domain.data.models.base.Bank
import com.example.cardbank.domain.data.models.base.BankBIN
import com.example.cardbank.data.local.room.DataBaseBank
import com.example.cardbank.data.local.room.HistoryBank

class RetrofitRequestImpl(private val api: BinApi) : RepositoryBank {

    override suspend fun observerBin(bin: String): BankBIN {
        return api.bankBin(bin)
    }

    override suspend fun saveBin(bank: Bank) {
        DataBaseBank.db.bankDao().insert(convertToEntity(bank))
    }

    private fun convertToEntity(bank: Bank): HistoryBank {
        return HistoryBank(0,
            bank.name,
            bank.city,
            bank.phone,
            bank.url
        )
    }
}