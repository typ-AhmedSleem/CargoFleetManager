package com.typ.cargo.features.gamepad

import com.studiohartman.jamepad.ControllerIndex
import com.studiohartman.jamepad.ControllerManager
import com.studiohartman.jamepad.ControllerPowerLevel
import com.studiohartman.jamepad.ControllerState
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.channelFlow

actual class GamepadController {

    private var controllerManager: ControllerManager = ControllerManager()
        .apply {
            initSDLGamepad()
        }

    val controllersCount: Int
        get() {
            controllerManager.update()
            return controllerManager.numControllers
        }

    private val controller: ControllerIndex
        get() {
            controllerManager.update()
            return controllerManager.getControllerIndex(0)
        }

    actual fun connected(): Boolean {
        return controllersCount > 0
    }

    val powerLevel: ControllerPowerLevel
        get() {
            return if (controller.isConnected) {
                controller.powerLevel ?: ControllerPowerLevel.POWER_UNKNOWN
            } else {
                ControllerPowerLevel.POWER_UNKNOWN
            }
        }

    fun captureState(index: Int = 0): ControllerState {
        return controllerManager.getState(index)
    }

    actual fun performHapticFeedback(leftMotor: Float, rightMotor: Float, duration: Long): Boolean {
        return controller.isConnected && controller.doVibration(leftMotor, rightMotor, duration.toInt())
    }

    fun subscribe() = channelFlow {
        while (true) {
            val state = captureState()
            if (state.isConnected) {
                trySendBlocking(ControllerSnapshot.Snapshot(state, powerLevel))
                    .onFailure {
                        println("Failed to send ControllerSnapshot.Snapshot")
                    }
                    .onClosed {
                        println("ControllerSnapshot.Snapshot channel closed")
                    }
            } else {
                trySendBlocking(ControllerSnapshot.Disconnected)
                    .onFailure {
                        println("Failed to send ControllerSnapshot.Disconnected")
                    }
                    .onClosed {
                        println("ControllerSnapshot.Disconnected channel closed")
                    }
                close(Exception("Controller disconnected"))
            }
        }
    }

    fun dispose() {
        try {
            controllerManager.quitSDLGamepad()
            println("GamepadController disposed")
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun searchForControllers(): Boolean {
        return connected()
    }

}