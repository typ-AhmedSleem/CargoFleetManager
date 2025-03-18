package com.typ.cargo

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication

fun main() =     singleWindowApplication(
    title = "CargoFleetManager",
) {
    App()
}