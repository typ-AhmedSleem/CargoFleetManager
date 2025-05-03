package com.typ.cargo.ui.screens.account

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cargofleetmanager.composeapp.generated.resources.Res
import cargofleetmanager.composeapp.generated.resources.picAhmedSleem
import com.typ.cargo.data.models.User
import com.typ.cargo.data.repositories.abstractions.UserRepository
import com.typ.cargo.ui.screens.dashboard.DashboardScreen
import com.typ.cargo.ui.theming.Colors
import io.github.alexzhirkevich.cupertino.CupertinoBorderedTextField
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.respawn.kmmutils.compose.resources.painter

@OptIn(ExperimentalCupertinoApi::class)
object LoginScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val userRepo: UserRepository = remember { get() }
        val navigator = LocalNavigator.current
        val coroutineScope = rememberCoroutineScope()
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        var loggedUser: User? by remember { mutableStateOf(null) }
        var loginStatus by remember { mutableStateOf(LoginState.NOT_LOGIN) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.CenterVertically,
                space = 8.dp,
            ),
        ) {
            AnimatedContent(
                contentAlignment = Alignment.Center,
                modifier = Modifier.wrapContentHeight(),
                targetState = loginStatus == LoginState.LOGGED_IN,
            ) { loggedIn ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        alignment = Alignment.CenterVertically,
                        space = 8.dp,
                    ),
                ) {
                    if (loggedIn) {
                        Image(
                            modifier = Modifier
                                .size(160.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 2.dp,
                                    shape = CircleShape,
                                    color = Colors.secondarySystemBackground,
                                ),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Ahmed Sleem",
                            painter = Res.drawable.picAhmedSleem.painter(),
                        )

                        Spacer(Modifier.height(8.dp))

                        CupertinoText(
                            maxLines = 1,
                            text = "Welcome!",
                            color = Colors.secondaryLabel,
                            style = CupertinoTheme.typography.largeTitle,
                        )

                        CupertinoText(
                            maxLines = 1,
                            fontSize = 56.sp,
                            color = Colors.label,
                            textAlign = TextAlign.Center,
                            style = CupertinoTheme.typography.largeTitle,
                            text = loggedUser!!.name,
                        )

                        Spacer(Modifier.height(16.dp))
                    } else {
                        CupertinoText(
                            maxLines = 1,
                            text = "Login to CFM",
                            style = CupertinoTheme.typography.largeTitle
                        )

                        Spacer(Modifier.height(2.dp))

                        CupertinoText(
                            maxLines = 3,
                            textAlign = TextAlign.Center,
                            style = CupertinoTheme.typography.title3,
                            color = CupertinoTheme.colorScheme.secondaryLabel,
                            text = "Enter your credentials so you can proceed to CFM",
                        )

                        Spacer(Modifier.height(16.dp))

                        InputField(
                            visualTransformation = VisualTransformation.None,
                            enabled = loginStatus == LoginState.NOT_LOGIN,
                            modifier = Modifier.width(300.dp),
                            placeholder = "Enter your username",
                            textState = username,
                        )

                        InputField(
                            visualTransformation = PasswordVisualTransformation(),
                            enabled = loginStatus == LoginState.NOT_LOGIN,
                            modifier = Modifier.width(300.dp),
                            placeholder = "Enter your password",
                            textState = password,
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            CupertinoButton(
                modifier = Modifier.widthIn(min = 300.dp),
                enabled = loginStatus != LoginState.LOGGING_IN &&
                        username.value.isNotEmpty() &&
                        password.value.isNotEmpty(),
                onClick = boc@{
                    if (loginStatus == LoginState.NOT_LOGIN) {
                        // * Validate inputs
                        if (username.value.isEmpty()) {
                            return@boc
                        }
                        if (password.value.isEmpty()) {
                            return@boc
                        }
                        coroutineScope.launch cs@{
                            loginStatus = LoginState.LOGGING_IN
                            delay(2500L)
                            userRepo.loginUser(
                                username = username.value,
                                password = password.value
                            ).onSuccess {
                                loggedUser = it
                                loginStatus = LoginState.LOGGED_IN
                            }.onFailure {
                                // todo: show error to user
                                it.printStackTrace()
                                loginStatus = LoginState.NOT_LOGIN
                            }
                        }
                    } else if (loginStatus == LoginState.LOGGED_IN) {
                        // * Proceed to next screen
                        navigator?.replaceAll(DashboardScreen)
                    }
                },
            ) {
                CupertinoText(
                    when (loginStatus) {
                        LoginState.LOGGING_IN -> "Logging you in..."
                        LoginState.LOGGED_IN -> "Continue to configuration"
                        else -> "Login to account"
                    }
                )
            }
        }
    }

    @Composable
    private fun InputField(
        enabled: Boolean,
        placeholder: String,
        modifier: Modifier = Modifier,
        textState: MutableState<String>,
        visualTransformation: VisualTransformation,
    ) {
        CupertinoBorderedTextField(
            enabled = enabled,
            modifier = modifier,
            value = textState.value.take(30),
            onValueChange = { textState.value = it },
            visualTransformation = visualTransformation,
            placeholder = { CupertinoText(text = placeholder) },
        )
    }

}