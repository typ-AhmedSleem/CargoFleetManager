package com.typ.cargo.di

import com.typ.cargo.data.repositories.abstractions.DashboardRepository
import com.typ.cargo.data.repositories.abstractions.UserRepository
import com.typ.cargo.data.repositories.mocks.MockDashboardRepository
import com.typ.cargo.data.repositories.mocks.MockUserRepository
import com.typ.cargo.networking.Endpoints
import com.typ.cargo.networking.createDefaultHttpClient
import com.typ.cargo.viewmodels.DashboardViewModel
import com.typ.cargo.workers.StreamWatcher
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { createDefaultHttpClient() }
    factory {
        StreamWatcher(
            client = get(),
            streamUrl = Endpoints.videoStream
        )
    }
    viewModel {
        DashboardViewModel(
            repository = get(),
            streamWatcher = get()
        )
    }
    single<DashboardRepository> { MockDashboardRepository() }
    single<UserRepository> { MockUserRepository() }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}