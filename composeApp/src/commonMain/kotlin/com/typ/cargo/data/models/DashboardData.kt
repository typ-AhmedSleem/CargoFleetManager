package com.typ.cargo.data.models

import com.typ.cargo.enums.DrivingMode

data class DashboardData(
    val batteryPercentage: Int,
    val leftMotorSpeed: Int,
    val rightMotorSpeed: Int,
    val driversTemperature: Int,
    val drivingMode: DrivingMode,
    val robotHeading: Int,
    val errorLogs: String?
)

