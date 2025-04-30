package com.typ.cargo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.typ.cargo.di.initKoin
import com.typ.cargo.ui.screens.dashboard.DashboardScreen
import com.typ.cargo.ui.theming.CFMTheme
import io.github.alexzhirkevich.cupertino.CupertinoScaffold
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun CFMAppEntryPoint() {
    initKoin()
    CFMAppContent()
}

@Composable
@OptIn(ExperimentalCupertinoApi::class)
private fun CFMAppContent() {
    var navigator = LocalNavigator.current
    var currentScreen = remember(navigator) { navigator?.lastItem }

    CFMTheme {
        CupertinoScaffold(
            modifier = Modifier.fillMaxSize(),
        ) { rootPaddings: PaddingValues ->
            Navigator(DashboardScreen) { nav ->
                SlideTransition(
                    nav,
                    modifier = Modifier.padding(rootPaddings)
                )

                navigator = nav
                currentScreen = nav.lastItem
            }

        }
    }
}