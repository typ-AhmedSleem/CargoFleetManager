package com.typ.cargo.features.gamepad

expect class GamepadController() {
    fun connected(): Boolean
    fun performHapticFeedback(leftMotor: Float, rightMotor: Float, duration: Long): Boolean
}