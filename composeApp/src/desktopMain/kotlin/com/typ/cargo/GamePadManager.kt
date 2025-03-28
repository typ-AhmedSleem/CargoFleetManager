package com.typ.cargo

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.ControllerListener
import com.badlogic.gdx.controllers.Controllers

class GamepadController : ControllerListener {

    override fun connected(controller: Controller) {
        println("🎮 Gamepad Connected: ${controller.name}")
    }

    override fun disconnected(controller: Controller) {
        println("🚫 Gamepad Disconnected: ${controller.name}")
    }

    override fun buttonDown(controller: Controller, buttonIndex: Int): Boolean {
        println("🔘 Button Pressed: $buttonIndex")
        return false
    }

    override fun buttonUp(controller: Controller?, buttonCode: Int): Boolean {
        println("🔘 Button Released: $buttonCode")
        return false
    }

    override fun axisMoved(controller: Controller, axisIndex: Int, value: Float): Boolean {
        println("🕹️ Joystick Moved - Axis: $axisIndex, Value: $value")
        return false
    }
}
