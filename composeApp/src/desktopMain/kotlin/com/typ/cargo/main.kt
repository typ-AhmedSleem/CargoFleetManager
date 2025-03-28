package com.typ.cargo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.singleWindowApplication
import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.Controllers
import com.studiohartman.jamepad.ControllerManager
import com.studiohartman.jamepad.ControllerState
import kotlinx.coroutines.delay

fun main() = singleWindowApplication(
    title = "CargoFleetManager",
) {
    val gamepadController = remember { GamepadController() }
    val manager = remember {
        ControllerManager().apply {
            initSDLGamepad()
        }
    }
    var controllerState by remember {
        mutableStateOf<ControllerState?>(null)
    }

    LaunchedEffect(Unit) {
        while (true) {
            if (manager.numControllers > 0) {
                controllerState = manager.getState(0)
                delay(5)
            }
            manager.update()
        }
    }

    MaterialTheme {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // * Display the controller state fields within Texts
            Text("Connected Controllers: ${manager.numControllers}")
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
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            manager.quitSDLGamepad()
        }
    }
}