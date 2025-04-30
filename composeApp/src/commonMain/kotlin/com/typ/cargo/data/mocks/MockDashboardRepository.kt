package com.typ.cargo.data.mocks

import com.typ.cargo.data.models.DashboardData
import com.typ.cargo.data.repositories.abstractions.DashboardRepository
import com.typ.cargo.enums.DrivingMode

class MockDashboardRepository : DashboardRepository {

    override suspend fun getDashboardData(): DashboardData {
        return DashboardData(
            batteryPercentage = 80,
            leftMotorSpeed = 50.0f,
            rightMotorSpeed = 50.0f,
            driversTemperature = 25,
            drivingMode = DrivingMode.MANUAL,
            robotHeading = 0,
            errorLogs = null
        )
    }

}
