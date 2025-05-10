package com.typ.cargo.ui.screens.gamepad

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.typ.cargo.ui.theming.CFMTheme
import io.github.alexzhirkevich.cupertino.CupertinoScaffold
import io.github.alexzhirkevich.cupertino.CupertinoText
import io.github.alexzhirkevich.cupertino.icons.CupertinoIcons
import io.github.alexzhirkevich.cupertino.icons.outlined.Battery100
import io.github.alexzhirkevich.cupertino.theme.CupertinoTheme
import kotlinx.coroutines.delay

class GamepadScreen : Screen {

    @Composable
    override fun Content() {
        var isConnected by remember { mutableStateOf(false) }
        var joystickX by remember { mutableStateOf(0f) }
        var joystickY by remember { mutableStateOf(0f) }
        var buttons by remember {
            mutableStateOf(
                mapOf(
                    "A" to true,
                    "B" to false,
                    "X" to true,
                    "Y" to false
                )
            )
        }
        var batteryLevel by remember { mutableStateOf(0) }
        var mode by remember { mutableStateOf("Unknown") }

        CFMTheme {
            CupertinoScaffold {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    GamepadControllerView(
                        isConnected = isConnected,
                        joystickX = joystickX,
                        joystickY = joystickY,
                        buttons = buttons,
                        batteryLevel = batteryLevel,
                        mode = mode,
                    )
                }
            }
        }

        LaunchedEffect(Unit) {
            delay(2000)
            isConnected = true
            buttons = mapOf(
                "A" to false,
                "B" to true,
                "X" to false,
                "Y" to true
            )
            batteryLevel = 100
            mode = "Wired connection"
            repeat(10) {
                joystickX = (-50..50).random().toFloat()
                joystickY = (-50..50).random().toFloat()
                delay(1000)
            }
        }
    }

    @Composable
    private fun GamepadControllerView(
        isConnected: Boolean,
        joystickX: Float,
        joystickY: Float,
        buttons: Map<String, Boolean>,
        batteryLevel: Int,
        mode: String
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.DarkGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            ConnectionIndicator(isConnected)
            BatteryIndicator(batteryLevel)
            ModeIndicator(mode)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                GameButton("A", buttons["A"] == true)
                GameButton("B", buttons["B"] == true)
                GameButton("X", buttons["X"] == true)
                GameButton("Y", buttons["Y"] == true)
            }
            Spacer(modifier = Modifier.height(16.dp))
            JoystickView(joystickX, joystickY) { x, y -> /* Handle Joystick Movement */ }
            JoystickTrail(joystickX, joystickY)
        }
    }

    @Composable
    private fun ConnectionIndicator(isConnected: Boolean) {
        val color by animateColorAsState(if (isConnected) Color.Green else Color.Red)
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color, CircleShape)
        )
    }

    @Composable
    private fun GameButton(label: String, isPressed: Boolean) {
        val scale by animateFloatAsState(if (isPressed) 1.2f else 1.0f)
        val color by animateColorAsState(if (isPressed) Color.Red else Color.Blue)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .scale(scale)
                .background(color, CircleShape)
        ) {
            CupertinoText(label, color = Color.White)
        }
    }

    @Composable
    private fun JoystickView(xOffset: Float, yOffset: Float, onMove: (Float, Float) -> Unit) {
        val offsetX by animateDpAsState(targetValue = xOffset.dp)
        val offsetY by animateDpAsState(targetValue = yOffset.dp)
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray, shape = CircleShape)
                .offset(offsetX, offsetY)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        onMove(dragAmount.x, dragAmount.y)
                        change.consume()
                    }
                }
        )
    }

    @Composable
    private fun JoystickTrail(x: Float, y: Float) {
        Canvas(modifier = Modifier.size(100.dp)) {
            val gradient = Brush.linearGradient(listOf(Color.Blue, Color.Cyan))
            drawLine(
                brush = gradient,
                start = center,
                end = center + Offset(x, y),
                strokeWidth = 4f
            )
        }
    }

    @Composable
    private fun BatteryIndicator(level: Int) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = CupertinoIcons.Default.Battery100,
                contentDescription = "Battery",
                tint = Color.Green
            )
            CupertinoText(
                text = "$level%",
                color = Color.White
            )
        }
    }

    @Composable
    private fun ModeIndicator(mode: String) {
        CupertinoText(
            text = mode,
            style = CupertinoTheme.typography.title3,
            color = Color.White
        )
    }

}