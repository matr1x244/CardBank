package com.example.cardbank.di.koin

import com.example.cardbank.data.web.BinApi
import com.example.cardbank.data.web.RetrofitRequestImpl
import com.example.cardbank.domain.data.models.RepositoryBank
import com.example.cardbank.domain.data.models.base.Bank
import com.example.cardbank.domain.data.models.room.RepositoryRoom
import com.example.cardbank.domain.data.models.room.RoomImpl
import com.example.cardbank.domain.data.models.viewmodels.BankRoomViewModels
import com.example.cardbank.domain.data.models.viewmodels.MainViewModels
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModuleKoin = module {

    val apiUrl = "https://lookup.binlist.net/"
    single<RepositoryBank> { RetrofitRequestImpl(get()) }
    single<BinApi> { get<Retrofit>().create(BinApi::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
    }

    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }

    single<RepositoryRoom> { RoomImpl() }

    viewModel { MainViewModels(get()) }
    viewModel { BankRoomViewModels(get()) }
}