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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.studiohartman.jamepad.ControllerPowerLevel
import com.typ.cargo.features.gamepad.ControllerSnapshot
import com.typ.cargo.features.gamepad.GamepadController
import io.github.alexzhirkevich.cupertino.CupertinoButton
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

object SBGamepadScreen : Screen {

    private fun readResolve(): Any = SBGamepadScreen

    @OptIn(ExperimentalCupertinoApi::class)
    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val controller = remember { GamepadController() }
        var powerLevel by remember {
            mutableStateOf(ControllerPowerLevel.POWER_UNKNOWN)
        }
        var controllerState by remember {
            mutableStateOf(controller.captureState())
        }
        var connected by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(connected) {
            if (connected) {
                println("Controller is connected and start to subscribe.")
                do {
                    connected = controllerState.isConnected
                    controllerState = controller.captureState()
                    powerLevel = controller.powerLevel
                } while (connected)

                controller
                    .subscribe()
                    .flowOn(kotlinx.coroutines.Dispatchers.Default)
                    .onStart {
                        println("Controller subscription has started.")
                    }
                    .onCompletion {
                        connected = false
                        println("Controller subscription has completed.")
                    }
                    .collect { snapshot ->
                        when (snapshot) {
                            is ControllerSnapshot.Snapshot -> {
                                connected = snapshot.controllerState.isConnected
                                controllerState = snapshot.controllerState
                                powerLevel = snapshot.powerLevel
                            }

                            is ControllerSnapshot.Disconnected -> {
                                connected = false
                                println("Controller has been disconnected.")
                            }
                        }
                    }.also {
                        println("Controller subscription has been launched.")
                    }
            }
        }

        MaterialTheme {
/*            AnimatedContent(
                targetState = connected,
                modifier = Modifier.fillMaxSize(),
            ) { isConnected ->*/
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    alignment = Alignment.CenterVertically,
                    space = 4.dp,
                ),
            ) {
                if (connected) {
                    // * Display the controller state fields within Texts
                    Text("Power level: $powerLevel")
                    Text("Button A: ${controllerState.a}")
                    Text("Button B: ${controllerState.b}")
                    Text("Button X: ${controllerState.x}")
                    Text("Button Y: ${controllerState.y}")
                    Text("Button L1: ${controllerState.lb}")
                    Text("Button R1: ${controllerState.rb}")
                    Text("Button L2: ${controllerState.leftTrigger}")
                    Text("Button R2: ${controllerState.rightTrigger}")
                    Text("Button Back: ${controllerState.back}")
                    Text("Button Start: ${controllerState.start}")
                    Text("Button Guide: ${controllerState.guide}")
                    Text("DPad Up: ${controllerState.dpadUpJustPressed}")
                    Text("DPad Down: ${controllerState.dpadDownJustPressed}")
                    Text("DPad Left: ${controllerState.dpadLeftJustPressed}")
                    Text("DPad Right: ${controllerState.dpadRightJustPressed}")
                    Text("Left Stick X: ${controllerState.leftStickX}")
                    Text("Left Stick Y: ${controllerState.leftStickY}")
                    Text("Right Stick X: ${controllerState.rightStickX}")
                    Text("Right Stick Y: ${controllerState.rightStickY}")
                    Text("Left Stick Magnitude: ${controllerState.leftStickMagnitude}")
                    Text("Right Stick Magnitude: ${controllerState.rightStickMagnitude}")
                    Text("Left Stick Angle: ${controllerState.leftStickAngle}")
                    Text("Right Stick Angle: ${controllerState.rightStickAngle}")
                    Text("Left Stick Click: ${controllerState.leftStickJustClicked}")
                    Text("Right Stick Click: ${controllerState.rightStickJustClicked}")

                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = {
                            controller.performHapticFeedback(
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
                            controller.performHapticFeedback(
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
                            controller.performHapticFeedback(
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
                            controller.performHapticFeedback(
                                leftMotor = 0f,
                                rightMotor = 1f,
                                duration = 500
                            )
                        }
                    ) {
                        Text("Perform right haptic vibration")
                    }
                } else {
                    // * Not connected * //
                    Text("Not connected")
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val foundController = controller.searchForControllers()
                                println("Found a controller: $foundController")
                                connected = foundController
                            }
                        }
                    ) {
                        Text(
                            if (connected) "Controller found"
                            else "Search for controllers"
                        )
                    }
                }
            }
//            }
        }

        DisposableEffect(Unit) {
            onDispose {
                controller.dispose()
            }
        }
    }
}