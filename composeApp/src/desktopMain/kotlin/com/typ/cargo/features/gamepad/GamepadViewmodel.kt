package com.typ.cargo.features.gamepad

import androidx.lifecycle.ViewModel
import com.studiohartman.jamepad.ControllerPowerLevel
import com.studiohartman.jamepad.ControllerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.onCompletion

class GamepadViewModel(
    private val controller: GamepadController,
) : ViewModel() {

    fun connected(): Boolean {
        return controller.connected()
    }

    fun controllersCount(): Int {
        return controller.controllersCount
    }

    fun getPowerLevel(): ControllerPowerLevel {
        return controller.powerLevel
    }

    fun captureState(index: Int = 0): ControllerState {
        return controller.captureState(index)
    }

    fun performHapticFeedback(leftMotor: Float, rightMotor: Float, duration: Long): Boolean {
        return controller.performHapticFeedback(leftMotor, rightMotor, duration)
    }

    fun subscribe(): Flow<ControllerSnapshot> = channelFlow {
        while (true) {
            val state = captureState()
            if (state.isConnected) {
                send(ControllerSnapshot.Snapshot(state, getPowerLevel()))
            } else {
                send(ControllerSnapshot.Disconnected)
                delay(1000)
                break
            }
        }
    }.onCompletion {
        println("GamepadViewModel: Subscription completed. Reason: $it")
    }

    fun dispose() {
        controller.dispose()
    }

}