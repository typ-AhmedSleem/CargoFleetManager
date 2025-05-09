package com.typ.cargo.features.gamepad

import com.studiohartman.jamepad.ControllerPowerLevel
import com.studiohartman.jamepad.ControllerState

sealed class ControllerSnapshot {
    data object Disconnected: ControllerSnapshot()
    data class Snapshot(val controllerState: ControllerState, val powerLevel: ControllerPowerLevel) : ControllerSnapshot()
}
