package com.example.cardbank.data.web

import com.example.cardbank.domain.data.models.base.BankBIN
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {

    @GET("{bin}")
    suspend fun bankBin(@Path("bin") bin: String): BankBIN
}