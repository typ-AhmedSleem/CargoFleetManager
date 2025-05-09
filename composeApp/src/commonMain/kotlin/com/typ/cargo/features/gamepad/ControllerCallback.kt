package com.typ.cargo.features.gamepad

abstract class ControllerCallback {
    abstract fun onConnected()
    abstract fun onDisconnected()
    abstract fun onButtonDown(button: Int): Boolean
    abstract fun onButtonUp(button: Int): Boolean
    abstract fun onAxisMoved(axis: GamepadAxis, value: Float, angle: Float): Boolean
}