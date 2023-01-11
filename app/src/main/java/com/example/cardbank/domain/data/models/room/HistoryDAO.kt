package com.example.cardbank.domain.data.models.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM bank_table")
    fun allHistory(): List<HistoryBank>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bank: HistoryBank)
}