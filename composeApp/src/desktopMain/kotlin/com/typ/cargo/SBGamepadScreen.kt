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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.studiohartman.jamepad.ControllerPowerLevel
import com.studiohartman.jamepad.ControllerState
import com.typ.cargo.features.gamepad.ControllerSnapshot.Disconnected
import com.typ.cargo.features.gamepad.ControllerSnapshot.Snapshot
import com.typ.cargo.features.gamepad.GamepadController
import com.typ.cargo.features.gamepad.GamepadViewModel
import com.typ.cargo.interpolation.LinearInterpolation
import com.typ.cargo.interpolation.LogarithmicInterpolation
import com.typ.cargo.interpolation.dampedInterpolation
import com.typ.cargo.interpolation.hybridInterpolation
import com.typ.cargo.interpolation.linearInterpolation
import com.typ.cargo.interpolation.logarithmicInterpolation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

object SBGamepadScreen : Screen {

    private fun readResolve(): Any = SBGamepadScreen

    @Composable
    override fun Content() {
        val controller = viewModel {
            GamepadViewModel(GamepadController())
        }

        var controllerState: ControllerState? by remember {
            mutableStateOf(null)
        }
        var powerLevel by remember {
            mutableStateOf(ControllerPowerLevel.POWER_UNKNOWN)
        }
        val linInterpolation = remember {
            LinearInterpolation(1f)
        }
        val logInterpolation = remember {
            LogarithmicInterpolation(0.25f)
        }

        LaunchedEffect(Unit) {
            println("LaunchedEffect: Entered LaunchedEffect.")
            controllerState = controller.captureState()
            println("LaunchedEffect: Captured initial controller state.")
            controller
                .subscribe()
                .flowOn(Dispatchers.Default)
                .collect {
                    when (it) {
                        is Snapshot -> {
                            controllerState = it.controllerState
                            powerLevel = it.powerLevel
                        }

                        Disconnected -> {
                            controllerState = null
                            powerLevel = ControllerPowerLevel.POWER_UNKNOWN
                        }
                    }
                }

            /*while (true) {
                if (controller.connected()) {
                    controllerState = controller.captureState()
                    powerLevel = controller.getPowerLevel()
                    println("LaunchedEffect: Controller state updated.")
                } else {
                    if (controllerState != null) {
                        controllerState = null
                    }
                    if (powerLevel != ControllerPowerLevel.POWER_UNKNOWN) {
                        powerLevel = ControllerPowerLevel.POWER_UNKNOWN
                    }
                    println("LaunchedEffect: Controller disconnected. Retrying to connect...")
                    delay(1000)
                }
            }*/
        }

        MaterialTheme {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                var previousRT by remember {
                    mutableIntStateOf(0)
                }
                // * Display the controller state fields within Texts
                Text("Connected Controllers: ${controllerState?.isConnected ?: false}")
                Text("Button A: ${controllerState?.a}")
                Text("Button B: ${controllerState?.b}")
                Text("Button X: ${controllerState?.x}")
                Text("Button Y: ${controllerState?.y}")
                Text("Button L1: ${controllerState?.lb}")
                Text("Button R1: ${controllerState?.rb}")
                Text("Button L2: ${processJoystickInput(controllerState?.leftTrigger ?: 0f, ::linearInterpolation) * 10}")
                Text("Button R2 (1): ${dampedInterpolation(controllerState?.rightTrigger ?: 0f, 1f)}")
                Text("Button R2 (HYBRID): ${
                    hybridInterpolation(controllerState?.rightTrigger ?: 0f, previousRT)
                        .also { previousRT = it }
                }"
                )
                Text("Button R2 (0.6): ${dampedInterpolation(controllerState?.rightTrigger ?: 0f, 0.6f)}")
                Text("Button R2 (0.5): ${dampedInterpolation(controllerState?.rightTrigger ?: 0f, 0.5f)}")
                Text("Button R2 (0.4): ${dampedInterpolation(controllerState?.rightTrigger ?: 0f, 0.4f)}")
                Text("Button R2 (0.3): ${dampedInterpolation(controllerState?.rightTrigger ?: 0f, 0.3f)}")
                Text("Button R2 (0.1): ${dampedInterpolation(controllerState?.rightTrigger ?: 0f, 0.1f)}")
                Text("Button Back: ${controllerState?.back}")
                Text("Button Start: ${controllerState?.start}")
                Text("Button Guide: ${controllerState?.guide}")
                Text("DPad Up: ${controllerState?.dpadUpJustPressed}")
                Text("DPad Down: ${controllerState?.dpadDownJustPressed}")
                Text("DPad Left: ${controllerState?.dpadLeftJustPressed}")
                Text("DPad Right: ${controllerState?.dpadRightJustPressed}")
                Text("Left Stick X (Raw): ${controllerState?.leftStickX}")
                Text("Left Stick X (LinV1): ${linInterpolation.interpolate(0f, controllerState?.leftStickX ?: 0f)}")
                Text("Left Stick X (LinV2): ${processJoystickInput(controllerState?.leftStickX ?: 0f, ::linearInterpolation) * 10}")
                Text("Left Stick X (LogV1): ${logInterpolation.interpolate(0f, controllerState?.leftStickX ?: 0f)}")
                Text("Left Stick X (LogV2): ${processJoystickInput(controllerState?.leftStickX ?: 0f, ::logarithmicInterpolation) * 10}")
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
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                controller.dispose()
            }
        }
    }

    private fun <O> processJoystickInput(rawInput: Float, interpolation: (Float, Float, Float, Float, Float) -> O): O {
        return interpolation(rawInput, 0f, 1f, -100.0f, 100.0f)
    }

}