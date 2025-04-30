package com.typ.cargo

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.ControllerListener
import com.studiohartman.jamepad.ControllerIndex
import com.studiohartman.jamepad.ControllerManager
import com.studiohartman.jamepad.ControllerState
import kotlinx.coroutines.delay

class GamepadController : ControllerListener {

    private var controller: ControllerIndex? = null
        private set(value) {
            if (value != null) {
                println("[GamepadController]: Controller has been connected successfully.")
            }
            field = value
        }
    private val controllerManager = ControllerManager()
        .apply {
            initSDLGamepad()
        }
        .also {
            if (it.numControllers >= 0) {
                controller = it.getControllerIndex(0)
            }
        }

    val controllersCount: Int
        get() = controllerManager.numControllers

    val anyControllerConnected: Boolean
        get() = controllersCount >= 0

    suspend fun grabControllerState(controllerIndex: Int = 0): ControllerState {
        val state = controllerManager.getState(controllerIndex)
        delay(5L)
        controllerManager.update()
        return state
    }

    fun performHapticFeedback(
        controllerIdx: Int = 0,
        leftMotor: Float,
        rightMotor: Float,
        duration: Int = 100
    ): Boolean {
        return controller?.doVibration(
            leftMotor,
            rightMotor,
            duration
        ).also { vibrated ->
            println("performHapticFeedback: canVibrate= ${controller?.canVibrate()}, vibrated= $vibrated")
        } == true
    }

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

    fun dispose() {
        try {
            controllerManager.quitSDLGamepad()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

}
