package com.example.cardbank.di.koin

import com.example.cardbank.data.local.room.RepositoryRoom
import com.example.cardbank.data.local.room.RoomImpl
import com.example.cardbank.domain.data.models.viewmodels.BankRoomViewModels
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val roomKoinModule = module {

    single<RepositoryRoom>(named("room")) { RoomImpl() }

    viewModel(named("room_view_model")) { BankRoomViewModels(get(named("room"))) }
}