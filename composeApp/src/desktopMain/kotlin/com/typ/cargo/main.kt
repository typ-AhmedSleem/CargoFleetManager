package com.typ.cargo

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowPosition.Aligned
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication(
    title = "CargoFleetManager",
    state = WindowState(
        position = Aligned(Alignment.Center),
        placement = WindowPlacement.Fullscreen,
        width = 1920.dp,
        height = 1080.dp
    )
) {
    CFMAppEntryPoint()
}