package com.example.cardbank

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.cardbank.di.koin.mainModuleKoin
import com.example.cardbank.di.koin.roomKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this // Room

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mainModuleKoin,roomKoinModule)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var appInstance: Context
    }
}