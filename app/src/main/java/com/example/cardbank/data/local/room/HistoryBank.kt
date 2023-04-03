package com.example.cardbank.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_table")
data class HistoryBank(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val city: String?,
    val name: String?,
    val phone: String?,
    val url: String?
)