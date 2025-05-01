package com.typ.cargo.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.typ.cargo.ui.screens.dashboard.DashboardScreen
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoIcon
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlinx.coroutines.delay

object LauncherScreen : Screen {

    @Composable
    @OptIn(ExperimentalCupertinoApi::class)
    override fun Content() {
        val navigator = LocalNavigator.current
        var uiReady by remember {
            mutableStateOf(false)
        }
        var statusText by remember {
            mutableStateOf("Starting CFM...")
        }
        var setupDone by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(Unit) {
            delay(1000L)
            uiReady = true
            delay(2000L)
            statusText = "Setting up app modules..."
            delay(2000L)
            statusText = "Setting up database..."
            delay(2000L)
            statusText = "Setting up networking..."
            delay(2000L)
            statusText = "Getting things ready..."
            delay(2000L)
            statusText = "Getting things ready..."
            delay(2000L)
            statusText = "Setup done\nCFM is ready!"
            setupDone = true
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            CupertinoText(
                fontSize = 75.sp,
                text = "Cargo Fleet Manager",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                style = CupertinoTheme.typography.largeTitle
            )

            Spacer(Modifier.height(32.dp))

            AnimatedVisibility(uiReady) {

                AnimatedContent(
                    targetState = statusText,
                    contentAlignment = Alignment.Center
                ) { status ->
                    CupertinoText(
                        text = status,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        style = CupertinoTheme.typography.title3
                    )
                }

            }

            Spacer(Modifier.height(4.dp))

            AnimatedVisibility(setupDone) {
                CupertinoButton(
                    onClick = {
                        navigator?.replace(DashboardScreen)
                    },
                    modifier = Modifier
                        .widthIn(min = 300.dp)
                        .heightIn(min = 48.dp),
                ) {
                    CupertinoText("Go to Dashboard")
                    Spacer(Modifier.width(8.dp))
                    CupertinoIcon(
                        contentDescription = "Go to Dashboard",
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward
                    )
                }
            }

        }
    }

}