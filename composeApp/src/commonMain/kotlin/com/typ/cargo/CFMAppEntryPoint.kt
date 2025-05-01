package com.typ.cargo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.typ.cargo.di.initKoin
import com.typ.cargo.ui.components.CFMTopAppBar
import com.typ.cargo.ui.screens.LauncherScreen
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
    var currentScreen: Screen by remember(navigator) {
        mutableStateOf(DashboardScreen)
    }

    CFMTheme {
        CupertinoScaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AnimatedVisibility(currentScreen !is LauncherScreen) {
                    CFMTopAppBar(
                        navigator = navigator,
                        currentScreen = currentScreen
                    )
                }
            }
        ) { rootPaddings: PaddingValues ->
            Navigator(DashboardScreen) { nav ->
                SlideTransition(
                    navigator = nav,
                    modifier = Modifier.padding(rootPaddings)
                ) {
                    currentScreen = it
                    it.Content()
                }

                navigator = nav
                currentScreen = nav.lastItem
            }

        }
    }
}