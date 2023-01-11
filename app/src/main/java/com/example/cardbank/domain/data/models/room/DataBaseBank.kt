package com.example.cardbank.domain.data.models.room

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cardbank.App

@androidx.room.Database(
    entities = [
        HistoryBank::class
    ],
    version = 1,
    exportSchema = false
)

abstract class DataBaseBank : RoomDatabase() {

    abstract fun bankDao(): HistoryDAO

    companion object {
        private const val DB_NAME = "add_database_bank.db"

        val db: DataBaseBank by lazy {
            Room.databaseBuilder(
                App.appInstance,
                DataBaseBank::class.java,
                DB_NAME
            ).build()
        }
    }

}