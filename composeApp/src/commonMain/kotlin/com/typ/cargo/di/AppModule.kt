package com.typ.cargo.di

import com.typ.cargo.data.mocks.MockDashboardRepository
import com.typ.cargo.data.repositories.abstractions.DashboardRepository
import com.typ.cargo.viewmodels.DashboardViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DashboardRepository> { MockDashboardRepository() }
    viewModel { DashboardViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}