package com.example.cardbank.data.local.room

import com.example.cardbank.domain.data.models.base.Bank

interface RepositoryRoom {

    suspend fun getAllHistory(): List<Bank>
}