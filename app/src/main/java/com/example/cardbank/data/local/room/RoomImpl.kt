package com.example.cardbank.data.local.room

import com.example.cardbank.domain.data.models.base.Bank

class RoomImpl : RepositoryRoom {

    override suspend fun getAllHistory(): List<Bank> {
        return convertHistoryEntityToUser(DataBaseBank.db.bankDao().allHistory())
    }

    private fun convertHistoryEntityToUser(bankList: List<HistoryBank>): List<Bank> {
        return bankList.map {
            Bank(it.name, it.phone, it.city, it.url)
        }
    }
}