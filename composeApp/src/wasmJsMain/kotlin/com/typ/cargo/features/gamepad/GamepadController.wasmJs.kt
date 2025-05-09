package com.typ.cargo.features.gamepad

actual class GamepadController {
    actual fun connected(): Boolean {
        TODO("Not yet implemented")
    }

    fun captureState(): PlatformControllerSnapshot {
        TODO("Not yet implemented")
    }

    actual fun performHapticFeedback(leftMotor: Float, rightMotor: Float, duration: Long): Boolean {
        TODO("Not yet implemented")
    }
}