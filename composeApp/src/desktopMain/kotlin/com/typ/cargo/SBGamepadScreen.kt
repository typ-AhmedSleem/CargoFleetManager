package com.typ.cargo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.studiohartman.jamepad.ControllerPowerLevel
import com.studiohartman.jamepad.ControllerState
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.takeFrom
import kotlinx.coroutines.delay

object SBGamepadScreen: Screen {

    private fun readResolve(): Any = SBGamepadScreen

    @Composable
    override fun Content() {
        val manager = remember { GamepadController() }
        var controllerState by remember {
            mutableStateOf<ControllerState?>(null)
        }
        var powerLevel by remember {
            mutableStateOf<ControllerPowerLevel?>(null)
        }

        LaunchedEffect(Unit) {
            while (true) {
                if (manager.anyControllerConnected) {
                    controllerState = manager.grabControllerState()
                    delay(5)
                    if (controllerState?.aJustPressed == true) {
                        manager.performHapticFeedback(
                            leftMotor = 0.8f,
                            rightMotor = 0.8f,
                            duration = 5000
                        )
//                    powerLevel = manager.controller?.powerLevel
                    }
                }
            }
        }

        MaterialTheme {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // * Display the controller state fields within Texts
                Text("Connected Controllers: ${manager.controllersCount}")
                Text("Button A: ${controllerState?.a}")
                Text("Button B: ${controllerState?.b}")
                Text("Button X: ${controllerState?.x}")
                Text("Button Y: ${controllerState?.y}")
                Text("Button L1: ${controllerState?.lb}")
                Text("Button R1: ${controllerState?.rb}")
                Text("Button L2: ${controllerState?.leftTrigger}")
                Text("Button R2: ${controllerState?.rightTrigger}")
                Text("Button Back: ${controllerState?.back}")
                Text("Button Start: ${controllerState?.start}")
                Text("Button Guide: ${controllerState?.guide}")
                Text("DPad Up: ${controllerState?.dpadUpJustPressed}")
                Text("DPad Down: ${controllerState?.dpadDownJustPressed}")
                Text("DPad Left: ${controllerState?.dpadLeftJustPressed}")
                Text("DPad Right: ${controllerState?.dpadRightJustPressed}")
                Text("Left Stick X: ${controllerState?.leftStickX}")
                Text("Left Stick Y: ${controllerState?.leftStickY}")
                Text("Right Stick X: ${controllerState?.rightStickX}")
                Text("Right Stick Y: ${controllerState?.rightStickY}")
                Text("Left Stick Magnitude: ${controllerState?.leftStickMagnitude}")
                Text("Right Stick Magnitude: ${controllerState?.rightStickMagnitude}")
                Text("Left Stick Angle: ${controllerState?.leftStickAngle}")
                Text("Right Stick Angle: ${controllerState?.rightStickAngle}")
                Text("Left Stick Click: ${controllerState?.leftStickJustClicked}")
                Text("Right Stick Click: ${controllerState?.rightStickJustClicked}")
                Text("Power level: $powerLevel")

                val client = HttpClient {
                    defaultRequest {
                        url {
                            takeFrom("https://localhost.com") // Base url
                        }
                    }
                    expectSuccess = true
                    install(HttpTimeout) {
                        val timeout = 30000L
                        connectTimeoutMillis = timeout
                        requestTimeoutMillis = timeout
                        socketTimeoutMillis = timeout
                    }
                    /*install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                        })
                    }*/
                }

                LaunchedEffect(Unit) {
                    client.get {
                        url {
                            takeFrom("https://localhost.com")
                        }
                    }.bodyAsText()
                        .also {
                            println("Response: $it")
                        }
                }

                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        manager.performHapticFeedback(
                            leftMotor = 0.5f,
                            rightMotor = 0.5f,
                            duration = 100
                        )
                    }
                ) {
                    Text("Perform full short haptic vibration")
                }
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        manager.performHapticFeedback(
                            leftMotor = 0.5f,
                            rightMotor = 0.5f,
                            duration = 100
                        )
                    }
                ) {
                    Text("Perform full long haptic vibration")
                }
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        manager.performHapticFeedback(
                            leftMotor = 1f,
                            rightMotor = 0f,
                            duration = 500
                        )
                    }
                ) {
                    Text("Perform left haptic vibration")
                }
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        manager.performHapticFeedback(
                            leftMotor = 0f,
                            rightMotor = 1f,
                            duration = 500
                        )
                    }
                ) {
                    Text("Perform right haptic vibration")
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                manager.dispose()
            }
        }
    }
}