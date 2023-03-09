package com.example.cardbank.di.koin

import com.example.cardbank.data.web.BinApi
import com.example.cardbank.data.web.RetrofitRequestImpl
import com.example.cardbank.domain.data.models.RepositoryBank
import com.example.cardbank.domain.data.models.viewmodels.MainViewModels
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModuleKoin = module {

    val apiUrl = "https://lookup.binlist.net/"
    single<RepositoryBank>(named("bank_api")) { RetrofitRequestImpl(get()) }
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

    viewModel(named("main_view_model")) { MainViewModels(get(named("bank_api"))) }
}