package com.example.cardbank.domain.data.models.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cardbank.domain.data.models.base.Bank
import com.example.cardbank.domain.data.models.base.Country

@Entity(tableName = "bank_table")
data class HistoryBank(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val city: String?,
    val name: String?,
    val phone: String?,
    val url: String?
)