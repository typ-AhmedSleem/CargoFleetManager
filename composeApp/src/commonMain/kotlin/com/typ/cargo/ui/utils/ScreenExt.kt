package com.typ.cargo.ui.utils

import cafe.adriel.voyager.core.screen.Screen
import com.typ.cargo.ui.screens.LauncherScreen
import com.typ.cargo.ui.screens.account.LoginScreen
import com.typ.cargo.ui.screens.dashboard.DashboardScreen

val Screen.shouldShowTopAppBar: Boolean
    get() = when(this) {
        is DashboardScreen -> true
        else -> false
    }

val Screen.title: String
    get() = when(this) {
        is LoginScreen -> "Login to account"
        is DashboardScreen -> "Cargo Fleet Manager"
        else -> ""
    }